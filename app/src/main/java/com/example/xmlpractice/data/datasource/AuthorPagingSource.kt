package com.example.xmlpractice.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.xmlpractice.data.model.Author

const val PAGING_SIZE = 4
//here use author name as the next token
data class PagingKey(val beforeId: String?, val afterId: String?)
class AuthorPagingSource(
    private val dataSource: IDataSource
): PagingSource<PagingKey, Author>() {
    override fun getRefreshKey(state: PagingState<PagingKey, Author>): PagingKey? {
        val anchorPosition = state.anchorPosition ?: return null
        //get the anchorposition - 1 and return all messages after that
        val author = state.closestItemToPosition(anchorPosition - 1) ?: return null
        return PagingKey(null, author.id)
    }

    override suspend fun load(params: LoadParams<PagingKey>): LoadResult<PagingKey, Author> {
        return try {
            //load size is automatically handled by paging library, mostly based on your viewport size.
            val userList = dataSource.getAuthorsPaging(
                params.key?.beforeId,
                params.key?.afterId,
                params.loadSize
            )
            //we just put the first item identifier as the beforeId, last item as the afterId,
            //backend will use beforeId to fetch all authors before this id, afterId to fetch
            //all authors after this id.
            LoadResult.Page(
                data = userList,
                //if the current direction is up, and the result list is smaller than page size, means no previous items available
                prevKey = if (params.key?.beforeId != null && userList.size < PAGING_SIZE) null else PagingKey(userList.firstOrNull()?.id, null),
                //if the current direction is down, and the result list is smaller than page size, means no next items available
                nextKey = if (params.key?.afterId != null && userList.size < PAGING_SIZE) null else PagingKey(null, userList.lastOrNull()?.id)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}
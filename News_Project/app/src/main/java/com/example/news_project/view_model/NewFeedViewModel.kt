import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_project.model.ListNewsFeed
import com.example.news_project.repository.Repository
import kotlinx.coroutines.launch

class NewFeedViewModel(private val repository: Repository) : ViewModel() {
    private val _newsFeed = MutableLiveData<ListNewsFeed>()
    val newsFeed: LiveData<ListNewsFeed> = _newsFeed

    init {
        viewModelScope.launch {
            val response = repository.getNewsFeed()
            if (response.isSuccessful) {
                _newsFeed.value = response.body()
                Log.d("AA", _newsFeed.value.toString())
            } else {
                Log.d("AA", response.errorBody().toString())
            }
        }
    }
}
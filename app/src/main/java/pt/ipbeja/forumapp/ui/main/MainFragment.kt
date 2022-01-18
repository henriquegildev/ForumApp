package pt.ipbeja.forumapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pt.ipbeja.forumapp.R
import pt.ipbeja.forumapp.databinding.MainFragmentBinding
import pt.ipbeja.forumapp.databinding.PostListItemBinding
import pt.ipbeja.forumapp.remote.Post
import pt.ipbeja.forumapp.resource.Resource
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId

val differ = object : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post) : Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post) : Boolean {
        return oldItem == newItem
    }
}

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()


    private val adapter: PostsAdapter = PostsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = MainFragmentBinding.inflate(inflater).let {
        this.binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.list.adapter = adapter

        // With Flow
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.posts.collect {
                    handleResourceUpdate(it)
                }
            }
        }
        // With LiveData
        /*viewModel.postsLD.observe(viewLifecycleOwner) {
            handleResourceUpdate(it)
        }*/

        binding.refresh.setOnRefreshListener {
            viewModel.fetchPosts()
        }
    }

    private fun handleResourceUpdate(it: Resource<List<Post>>?) {
        when (it) {
            Resource.Loading -> {
                binding.progress.isVisible = true
            }
            is Resource.Success -> {
                binding.refresh.isRefreshing = false // swipe to refresh
                binding.progress.isVisible = false
                adapter.submitList(it.data)
            }
        }
    }


    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var item: Post
        private val binding = PostListItemBinding.bind(view)

        init {
            view.setOnClickListener {
                val findNavController = findNavController()
                findNavController.navigate(MainFragmentDirections.actionMainFragmentToPostNavigation(item.id))
            }

            binding.likeBtn.setOnClickListener {
                // TODO call the appropriate API endpoint and increase the counter (or make a new request for a new dataset)
            }
        }

        fun bind(item: Post) {
            this.item = item
            binding.author.text = resources.getString(R.string.comment_author, item.user)
            binding.body.text = item.text
            binding.likeCounter.text = "${item.likes}"
            binding.comments.text = "${item.comments.size}"
            val dt = OffsetDateTime.ofInstant(Instant.ofEpochMilli(item.timestamp), ZoneId.systemDefault())
            binding.timestamp.text = dt.toString()

        }
    }

    inner class PostsAdapter() : ListAdapter<Post, PostViewHolder>(differ) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false)
            return PostViewHolder(v)
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

            val item = currentList[position]//getItem(position)
            holder.bind(item)
        }


    }

}
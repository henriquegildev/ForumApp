package pt.ipbeja.forumapp.ui.createpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipbeja.forumapp.R
import pt.ipbeja.forumapp.databinding.CreatePostFragmentBinding
import pt.ipbeja.forumapp.remote.Api
import pt.ipbeja.forumapp.remote.Post

class CreatePostFragment : Fragment() {

    private lateinit var binding: CreatePostFragmentBinding
    private lateinit var viewModel: CreatePostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = CreatePostFragmentBinding.inflate(inflater).let {
            this.binding = it
            it.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.submit.setOnClickListener{
            // TODO: Implement Posts
        }
    }

}
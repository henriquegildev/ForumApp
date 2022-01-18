package pt.ipbeja.forumapp.ui.viewpost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import pt.ipbeja.forumapp.R
import pt.ipbeja.forumapp.databinding.ViewPostFragmentBinding


// TODO Display the post and its comments
class ViewPostFragment : Fragment() {

    private val args : ViewPostFragmentArgs by navArgs()
    private val viewModel: ViewPostViewModel by navGraphViewModels(R.id.postNavigation, factoryProducer = { ViewPostViewModel.Factory(args.postId) })

    private lateinit var binding: ViewPostFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ViewPostFragmentBinding.inflate(inflater).let {
        this.binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
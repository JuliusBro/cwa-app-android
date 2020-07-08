package de.rki.coronawarnapp.ui.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.rki.coronawarnapp.R
import de.rki.coronawarnapp.databinding.FragmentFurtherRiskDetailsBinding

/**
 * Basic Fragment which only displays static content.
 */
class FurtherRiskDetailsFragment : Fragment() {
    companion object {
        private val TAG: String? = FurtherRiskDetailsFragment::class.simpleName
    }

    private var _binding: FragmentFurtherRiskDetailsBinding? = null
    private val binding: FragmentFurtherRiskDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFurtherRiskDetailsBinding.inflate(inflater)

        /*var textView = binding.furtherRiskDetailsText
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
            textView.setText(Html.fromHtml("file:///android_asset/risk_details_de.html", Html.FROM_HTML_MODE_LEGACY))

        } else {
            textView.setText(Html.fromHtml("file:///android_asset/risk_details_de.html"))
        }*/
        var mWebView = binding.websiteView
        mWebView.loadUrl(getString(R.string.further_risk_information_path))

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonOnClickListener()
    }

    private fun setButtonOnClickListener() {
    }
}

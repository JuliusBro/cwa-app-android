package de.rki.coronawarnapp.ui.test

import android.os.Bundle
import android.text.Html
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
            textView.setText(Html.fromHtml("file:///android_asset/risk_details.html", Html.FROM_HTML_MODE_LEGACY))

        } else {
            textView.setText(Html.fromHtml("file:///android_asset/risk_details.html"))
        }*/
        var mWebView = binding.websiteView
        mWebView.loadUrl("file:///android_asset/risk_details.html")
        //mWebView.loadUrl("https://github.com/FUB-HCC/20-SWP-CodingOpenness/wiki/Berechnung-des-Risk-Scores")
        //mWebView.settings.javaScriptEnabled = true

        //var markdownView = binding.markdownRiskDetails
        //markdownView.loadMarkdown(getString(R.string.further_risk_details_markdown))
        //markdownView.loadMarkdownFile("file:///android_asset/risk_details.md")
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

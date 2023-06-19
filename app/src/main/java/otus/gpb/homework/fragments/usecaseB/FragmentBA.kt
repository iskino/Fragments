package otus.gpb.homework.fragments.usecaseB

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import otus.gpb.homework.fragments.R

class FragmentBA : Fragment() {
    private var isLandscape: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isLandscape = (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        val resource = if (isLandscape) R.layout.fragment_ba_land else R.layout.fragment_ba

        return inflater.inflate(resource, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val containerViewId = if (isLandscape) R.id.inner_fragment_frame else R.id.fragment_bb_frame

        view.findViewById<AppCompatButton>(R.id.button_open_fragment_bb)?.apply {
            visibility = if (isLandscape) View.GONE else View.VISIBLE

            setOnClickListener {
                childFragmentManager
                    .beginTransaction()
                    .add(containerViewId, FragmentBB())
                    .addToBackStack(null)
                    .commit()
                Log.d("app", "clicked button to open fragment_bb")
            }
        }

        setFragmentResultListener("key") { _, bundle ->
            val color = bundle.getInt("color")
            val resource = if (isLandscape) R.id.fragment_ba_land_frame else R.id.fragment_ba_frame

            view.findViewById<FrameLayout>(resource)?.background = color.toDrawable()
        }
    }
}
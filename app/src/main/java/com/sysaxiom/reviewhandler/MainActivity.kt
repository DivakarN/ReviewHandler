package com.sysaxiom.reviewhandler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.testing.FakeReviewManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val manager = ReviewManagerFactory.create(this)
        val manager = FakeReviewManager(this)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                // We got the ReviewInfo object
                val reviewInfo = request.result
                review(manager, reviewInfo)
            } else {
                // There was some problem, continue regardless of the result.
            }
        }

    }

    private fun review(manager: ReviewManager, reviewInfo: ReviewInfo) {
        val flow = manager.launchReviewFlow(this, reviewInfo)
        flow.addOnCompleteListener { _ ->
            // The flow has finished. The API does not indicate whether the user
            // reviewed or not, or even whether the review dialog was shown. Thus, no
            // matter the result, we continue our app flow.
        }
    }
}
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.databinding.InterviewQuestionsBinding
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestion

class InterviewQuestionAdapter(
    private var interviewQueList: ArrayList<InterviewQuestion>
) : RecyclerView.Adapter<InterviewQuestionAdapter.InterviewQueViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterviewQueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
    }

    override fun getItemCount(): Int = interviewQueList.size

    override fun onBindViewHolder(holder: InterviewQueViewHolder, position: Int) {
    }
}

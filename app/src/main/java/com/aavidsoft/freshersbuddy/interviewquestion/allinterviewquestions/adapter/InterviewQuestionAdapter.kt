import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.databinding.InterviewQuestionsBinding
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestion

class InterviewQuestionAdapter(
    private var interviewQueList: ArrayList<InterviewQuestion>
) : RecyclerView.Adapter<InterviewQuestionAdapter.InterviewQueViewHolder>() {

    inner class InterviewQueViewHolder(val binding: InterviewQuestionsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterviewQueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = InterviewQuestionsBinding.inflate(layoutInflater, parent, false)
        return InterviewQueViewHolder(binding)
    }

    override fun getItemCount(): Int = interviewQueList.size

    override fun onBindViewHolder(holder: InterviewQueViewHolder, position: Int) {
        val interviewQuestion = interviewQueList[position]
        holder.binding.interviewQuestionObject = interviewQuestion
    }
}

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.databinding.InterviewQuestionsBinding
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestion

class InterviewQuestionAdapter(
    private var interviewQuestionList: ArrayList<InterviewQuestion>
) : RecyclerView.Adapter<InterviewQuestionAdapter.InterviewQuestionViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(
            interviewQuestion: InterviewQuestion,
            position: Int,
            interviewQuestionAdapter: InterviewQuestionAdapter
        )
    }

    var onItemClickListener: OnItemClickListener? = null

    inner class InterviewQuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val interviewQuestionBinding = InterviewQuestionsBinding.bind(view)

        init {
            interviewQuestionBinding.root.setOnClickListener {
                onItemClickListener?.onItemClick(
                    interviewQuestionList[adapterPosition],
                    adapterPosition,
                    this@InterviewQuestionAdapter
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): InterviewQuestionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.interview_questions,parent,false)
        return InterviewQuestionViewHolder(view)
    }

    override fun getItemCount(): Int = interviewQuestionList.size

    override fun onBindViewHolder(holder: InterviewQuestionViewHolder, position: Int
    ) {
        val interviewQuestion = interviewQuestionList[position]
        holder.interviewQuestionBinding.interviewQuestionObject = interviewQuestion
    }
}

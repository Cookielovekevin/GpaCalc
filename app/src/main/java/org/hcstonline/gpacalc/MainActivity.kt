package org.hcstonline.gpacalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class Course constructor(private val year: Int,
                         private val name: String,
                         private val credits: Double,
                         private var grade: Int,
                         private val ap: Boolean) {

    fun numToLetter(): String {
        if (grade in 97..105) return "A+"
        if (grade > 94) return "A"
        if (grade > 89) return "A-"
        if (grade > 86) return "B+"
        if (grade > 83) return "B"
        if (grade > 79) return "B-"
        if (grade > 76) return "C+"
        if (grade > 73) return "C"
        if (grade > 69) return "C-"
        else return "NP"
    }

    fun letterToGpaPoints(): Double {
        // A+ = 4.5, A = 4.25 A- = 4.0
        var gpapoints = 0.0

        if (numToLetter() == "A+") gpapoints = 4.5
        else if (numToLetter() == "A") gpapoints = 4.0
        else if (numToLetter() == "A-") gpapoints = 3.75
        else if (numToLetter() == "B+") gpapoints = 3.50
        else if (numToLetter() == "B") gpapoints = 3.25
        else if (numToLetter() == "B-") gpapoints = 3.0
        else if (numToLetter() == "C+") gpapoints =  2.75
        else if (numToLetter() == "C-") gpapoints = 2.5
        else 0.0

        if(ap) gpapoints += 1.0
        return gpapoints
    }

    fun qpoints(): Double {
        return letterToGpaPoints() * credits
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var gradeLevelInput = findViewById<EditText>(R.id.grade_Level)
        var courseNameInput = findViewById<EditText>(R.id.courseName)
        var creditsEarnedInput = findViewById<EditText>(R.id.creditsEarned)
        var courseGradeInput = findViewById<EditText>(R.id.courseGrade)
        var isApInput = findViewById<CheckBox>(R.id.apCheck)
        val calcBtn = findViewById<Button>(R.id.addCourse)

        var letterGradeView = findViewById<TextView>(R.id.letterGradeView_id)
        var gpaPointsView = findViewById<TextView>(R.id.gpaPointsView_id)
        var qPointsView = findViewById<TextView>(R.id.qPointsView_id)
        calcBtn.setOnClickListener {
            if(
                gradeLevelInput.toString().isNotEmpty() &&
                courseNameInput.toString().isNotEmpty() &&
                creditsEarnedInput.toString().isNotEmpty() &&
                courseGradeInput.toString().isNotEmpty()
            )
            {
                var course = Course(gradeLevelInput.text.toString().toInt(),
                    courseNameInput.text.toString(),
                    creditsEarnedInput.text.toString().toDouble(),
                    courseGradeInput.text.toString().toInt(),
                    isApInput.isChecked)
                course.letterToGpaPoints()
                letterGradeView.text = "Letter Grade: " + course.numToLetter()
                gpaPointsView.text = "Gpa Points: " + course.letterToGpaPoints()
                qPointsView.text = "Quality Points: " + course.qpoints()

            }
        }
    }
}
package com.labs7.firebaserealtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var btn:Button
    lateinit var  TVname: TextView

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = database.reference.child("Users")
    val reference2 : DatabaseReference = database.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextName)
        btn = findViewById(R.id.button)
        TVname=findViewById(R.id.textViewNAme)


        reference2.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //this method continuosly monitors any changes in the database
             val realName:String =    snapshot.child("Users").child("Name").value as String
                TVname.text = realName

            }

            override fun onCancelled(error: DatabaseError) {
                //this is for if some error came and data can't be recevied then some toast maybe showed.
            }
        })


        btn.setOnClickListener {

            val name:String = editText.text.toString()
            //this will create a child key "username" and value "${name}"
            reference.child("Name").setValue(name)




        }




    }
}
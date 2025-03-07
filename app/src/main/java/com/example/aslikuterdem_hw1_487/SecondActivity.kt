package com.example.aslikuterdem_hw1_487

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aslikuterdem_hw1_487.databinding.ActivityMainBinding
import com.example.aslikuterdem_hw1_487.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    private var txtreverse:String=""
    private lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySecondBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val pieceName = result.data?.getStringExtra("piece_name")
                if (!pieceName.isNullOrEmpty()) {
                    // Process the returned piece_name value
                    binding.tvNew.text = "Formerly Selected Piece: $pieceName"
                }
            }
        }

        val spinnerLevelItem= listOf("Beginner","Intermediate","Advanced")
        val spinnerEraItem=listOf("Baroque","Classical","Romantic")
        val adapterEra=ArrayAdapter(this,R.layout.spinner_era,spinnerEraItem)
        adapterEra.setDropDownViewResource(R.layout.spinner_era)
        val adapterLevel=ArrayAdapter(this,R.layout.spinner_level,spinnerLevelItem)
        adapterLevel.setDropDownViewResource(R.layout.spinner_level)

        binding.spinEra.adapter=adapterEra
        binding.spinLevel.adapter=adapterLevel
        val igIdEra= intArrayOf(R.drawable.barok,R.drawable.classic,R.drawable.rom)
        val igIdLvl= intArrayOf(R.drawable.beginner,R.drawable.inter,R.drawable.exprt)

        val nm=intent.getStringExtra("name")
        val instr=intent.getStringExtra("instrument")
        binding.tvFirst.text="Hello $nm\nPlease Select Further For $instr\n$txtreverse"


        binding.spinEra.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.ivEra.setImageResource(igIdEra[position])}

            override fun onNothingSelected(parent: AdapterView<*>?) {
                makeShowDialog("Please Do Selection on Era's")
            }
        }
        binding.spinLevel.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.ivLevel.setImageResource(igIdLvl[position])
                }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                makeShowDialog("Please Do Selection on Level's")
            }
        }
        binding.btnRec.setOnClickListener{
            val selera=binding.spinEra.selectedItem.toString()
            val sellvl=binding.spinLevel.selectedItem.toString()
            if(selera.isNotEmpty() && sellvl.isNotEmpty()){
                val intent= Intent(this,ThirdActivity::class.java)
                intent.putExtra("era",selera)
                intent.putExtra("lvl",sellvl)
                intent.putExtra("instr",instr)

                startForResult.launch(intent)
            }else
                makeShowDialog("Please do Selection")
        }


    }
    private fun makeShowDialog(msg:String?){
        AlertDialog.Builder(this).setTitle("ERROR")
            .setMessage(msg)
            .setPositiveButton("Close"){dialog,_->dialog.dismiss()}
            .create()
            .show()
    }
    }


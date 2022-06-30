package com.candra.trainingkopdig.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.candra.trainingkopdig.R
import com.candra.trainingkopdig.databinding.DialogFragmentCartBinding
import com.candra.trainingkopdig.helper.Animation
import java.text.DecimalFormat

class CartActivity: AppCompatActivity()
{

    private var selectedValue = ""
    private val isValid = mutableListOf(false,false,false,false,false)
    private lateinit var binding: DialogFragmentCartBinding
    private val defaultStringBuy = "Beli sekarang"
    private var formatData = ""
    private var selectedValuePayment = ""
    private var valueNote = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogFragmentCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validationData()

        val intentData = intent.getStringExtra("data")
        binding.textInputNameCoffee.editText?.setText(intentData)

        setDefaultPayment()

        setDefaultKindCoffee()


    }

    private fun setDefaultKindCoffee(){
        binding.rbBubuk.isChecked = true
    }

    @SuppressLint("SetTextI18n")
    private fun validationData(){
        with(binding){

            edtRekening.setText(getString(R.string.nmr_rekening))

            chipTunai.setOnClickListener {
                setVisibility(false)
            }
            chipNonTunai.setOnClickListener {
                setVisibility(true)
            }

            textEditName.doAfterTextChanged {
                val nameValid = it?.isNotBlank()?: false
                isValid[0] = nameValid
                textInputName.apply {
                    if (!nameValid) error = "Nama perlu diisi"
                    isErrorEnabled = !nameValid
                }
                validationButton()
            }


            textEditAlamat.doAfterTextChanged {
                val alamatValid = it?.isNotBlank()?: false
                isValid[1] = alamatValid
                textInputAlamat.apply {
                    if (!alamatValid) error = "Alamat perlu diisi"
                    isErrorEnabled = !alamatValid
                }
                validationButton()
            }
            textEditNmrHp.doAfterTextChanged {
                val nmrHpValid = it?.isNotBlank()?: false
                isValid[2] = nmrHpValid
                textInputNmrHp.apply {
                    if (!nmrHpValid) error = "Nmr Hp perlu diisi"
                    isErrorEnabled = !nmrHpValid
                }
                validationButton()
            }

            textEditSachet.doAfterTextChanged {
                val validSachet = it?.trim().toString().lowercase().isNotEmpty() ?: false
                isValid[3] = validSachet
                textInputSachet.apply {
                    if (!validSachet){
                        error = "Sachet perlu diisi"
                        isErrorEnabled = !validSachet
                        btnConfirmation.text = defaultStringBuy
                    }else{
                        val confirmationText = it?.trim().toString()
                        val validationText = confirmationText.toInt() * 45000
                        formatData = DecimalFormat("#,###").format(validationText)
                        btnConfirmation.text = "$defaultStringBuy Rp. $formatData"
                        isErrorEnabled = false
                    }
                }
                validationButton()
            }

            textEditWa.doAfterTextChanged {
                val validWa = it?.isNotBlank()?: false
                isValid[4] = validWa
                textInputWa.apply {
                    if (!validWa) error = "Nomor wa perlu diisi"
                    isErrorEnabled = !validWa
                }
                validationButton()
            }

            binding.textInputNameCoffee.isEnabled = false


            btnConfirmation.text = defaultStringBuy

            backCardView.setOnClickListener { onBackPressed() }

            btnConfirmation.setOnClickListener {
                val name = textInputName.editText?.text.toString()
                val alamat = textInputAlamat.editText?.text.toString()
                val nmrHp = textInputNmrHp.editText?.text.toString()
                val nmrWa = textInputWa.editText?.text.toString()
                val sachet = textInputSachet.editText?.text.toString()
                val nameCoffee = textInputNameCoffee.editText?.text.toString()
                val note = textInputNote.editText?.text.toString()

                if (note.isEmpty()){
                    valueNote = "Tidak ada catatan"
                }else{
                    valueNote = note
                }

                if (rgPilihanItem.checkedRadioButtonId > 0){
                    when(rgPilihanItem.checkedRadioButtonId){
                        R.id.rb_biji -> selectedValue = "Biji Kopi"
                        R.id.rb_bubuk -> selectedValue = "Bubuk Kopi"
                    }
                }else{
                    selectedValue = "nothing value"
                }

                if(chipTunai.isChecked){
                    selectedValuePayment = chipTunai.text.toString()
                }else if (chipNonTunai.isChecked){
                    selectedValuePayment =  chipNonTunai.text.toString()
                }
                sendMessageToWA(name,alamat,nmrHp,nmrWa,sachet,selectedValue,nameCoffee,valueNote,selectedValuePayment)
            }

        }
    }


    private fun validationButton(){
        binding.btnConfirmation.isEnabled = isValid.filter { it }.size == 5
    }

    private fun setVisibility(show: Boolean){
        binding.textInputRekening.visibility = if (show) View.VISIBLE else View.GONE
        binding.catatanNmrRekening.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun sendMessageToWA(
        name: String,
        alamat: String,
        nmrHp: String,
        nmrWa: String,
        sachet: String,
        kindCoffee: String,
        nameCoffee: String,
        note: String,
        pay: String
    )
    {
        val phone = 6282311558341
        val sendMessage = "Nama Kopi: $nameCoffee \n Nama: $name, \n Alamat: $alamat, \n Nmr HP: $nmrHp, \n Nmr WA: $nmrWa, \n Sachet: $sachet, \n Jenis Kopi: $kindCoffee, \n Harga: $formatData" +
                "\n Catatan: $note \n Pembayaran: $pay"
        val parsingUri = Uri.parse("https://wa.me/$phone?text=$sendMessage")
        if (Animation.appInstalled(this@CartActivity,"com.whatsapp")){
            Intent(Intent.ACTION_VIEW,parsingUri).also { startActivity(it) }
        }else{
            Toast.makeText(this@CartActivity,"App is not installed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDefaultPayment(){
        binding.apply {
            chipTunai.isChecked = true
            setVisibility(show = false)
        }
    }

}
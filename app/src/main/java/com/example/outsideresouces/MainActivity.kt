package com.example.outsideresouces

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.outsideresouces.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //essa classe é utilizada para configurar a interface de usuário
        binding = ActivityMainBinding.inflate(layoutInflater)
        //defino o layout e configuro de tal forma que não preciso usar findById
        setContentView(binding.root)

        // essa variável gurda a implementação de ProductsService
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(ProductsService::class.java)

        /*
        * Tudo o que está dentro desse bloco é executado em um contexto de corrotina,
        * o que significa que você pode chamar funções suspend diretamente.
        * */
        val responseLiveData: LiveData<Response<Products>> = liveData {

            // que faz uma chamada de rede para o endpoint definido na sua interface ProductsService.
            val response  = retrofitService.getProducts()

            //Isso significa que assim que a chamada de rede for concluída e a
            // resposta for recebida, o LiveData emitirá essa resposta,
            // que poderá ser observada e usada para atualizar a interface do usuário.
            emit(response)
        }

        responseLiveData.observe(/* owner = */ this, Observer {
            val productsList  = it.body()?.listIterator()
            if (productsList != null) {
                while (productsList.hasNext()) {
                    val productsItem = productsList.next()

                    val productName = "NOME DO PRODUTO : ${productsItem.name}\n"
                    val productPrice = "PREÇO DO PRODUTO : ${productsItem.price_in_cents}\n"

                    binding.titleTextView.append(productName)
                    binding.titleTextView.append(productPrice)

                    /*for (product in productsList) {
                        // Inflate the item_product layout to create a new CardView for each product
                        val cardView = LayoutInflater.from(this).inflate(R.layout.activity_main, binding.root, false)

                        // Get the TextViews from the inflated layout
                        val productNameTextView = cardView.findViewById<TextView>(R.id.titleTextView)
                        val productPriceTextView = cardView.findViewById<TextView>(R.id.titleTextViewPrice)

                        // Set the product name and price to the TextViews
                        productNameTextView.text = product.name
                        productPriceTextView.text = product.price_in_cents.toString()

                        // Add the CardView to the parent layout (for example, a LinearLayout)
                        binding.parentLayout.addView(cardView)
                    }*/
                }
            }

        })
    }
}
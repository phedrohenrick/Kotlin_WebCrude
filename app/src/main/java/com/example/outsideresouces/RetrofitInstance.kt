package com.example.outsideresouces

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//biblioteca retrofit que faz requisições Http no android
class RetrofitInstance {

    //bloco estática onde não preciso de instância
    companion object{

        //URL para requisição na api, neste caso ´10.0.2.2 é o endereço para acessar o localhost da máquina pelo emulador
        val mainURL = "http://10.0.2.2:8080/"

        fun getRetrofitInstance(): Retrofit{// cria e configura o obejto que será usado para chamadas na api
            return Retrofit.Builder()//inicia o obejto
                //O formato JSON representa dados estruturados em pares chave-valor
                .baseUrl(mainURL)
                // usando a biblioteca json converte o json em objeto kotlin
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                //cria e retorna a instancia retrofit com as configurações feitas
                .build()
        }
    }
}
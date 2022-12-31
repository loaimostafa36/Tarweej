import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.tarweej.domain.interactors.LoginInteractorImp
import com.example.tarweej.domain.sherPref.SharedPrefHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

private lateinit var sharedPrefHelper: SharedPrefHelper
var token :String?=null



fun apolloClient(context: Context):ApolloClient{
    sharedPrefHelper= SharedPrefHelper(context)
    token = sharedPrefHelper.getString("token")

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor(context))
        .build()

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://staging-api.trwej.com/graphql")
        .okHttpClient(okHttpClient)
        .build()

    return apolloClient
}

private class AuthorizationInterceptor(val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        if (token!=null && token?.isNotEmpty() == true){
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer "+token.toString())
                .build()
            return chain.proceed(request)
//        }
//        val request = chain.request().newBuilder()
//            .build()
//        return chain.proceed(request)

    }
}


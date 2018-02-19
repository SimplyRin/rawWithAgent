package net.simplyrin.kotlin

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

import org.apache.commons.io.IOUtils

class Main {

	fun main(args: Array<String>) {
		if(args.size > 0) {
			this.rawWithAgent(args[0])
			return
		}
		println("Args: java -jar kotlin.jar <url>")
	}

	fun rawWithAgent(url: String) : String {
		println("Fetching: " + url)
		try {
			var u = URL(url)
			var connection = u.openConnection() as HttpURLConnection
			connection.requestMethod = "GET"
			connection.useCaches = true
			connection.addRequestProperty("User-Agent", "Mozilla/4.7.6")
			connection.readTimeout = 15000
			connection.connectTimeout = 15000
			connection.doOutput = true
			var inputStream = connection.inputStream
			var encoding = Charset.defaultCharset()
			var s = IOUtils.toString(inputStream, encoding)
			if(s != null) {
				return s
			}
		} catch (e: Exception) {
			e.printStackTrace()
		}
		return ""
	}

}

package GithubData

import scala.io.Source

object UrlConnection extends App {

	case class UrlConnection(username: String) {
		def urlConnection: String = {
			Source.fromURL(s"https://api.github.com/users/$username/starred").mkString
		}
	}

}

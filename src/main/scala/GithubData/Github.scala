package GithubData

import io.circe.Json
import io.circe._
import io.circe.parser._
import io.circe.syntax._

object Github extends App {

	val githubUser1 = UrlConnection.UrlConnection("ggandra")
	val githubData1 = githubUser1.urlConnection

	case class Starred(name: String, full_name: String, html_url: String, owner: Owner, homepage: Option[String])

	case class Owner(login: String, id: BigInt, url: String)

	implicit val ownerDecoder: Decoder[Owner] = Decoder.forProduct3(
		"login",
		"id",
		"url"
	)(Owner.apply)

	implicit val starredDecoder: Decoder[Starred] = Decoder.forProduct5(
		"name",
		"full_name",
		"html_url",
		"owner",
		"homepage"
	)(Starred.apply)
	println(githubData1)

//	implicit val starredCodec: Codec[List[Starred]] = deriveCodec[List[Starred]]
//	implicit val ownerCodec: Codec[Owner] = deriveCodec[Owner]

//	println(starredDecoder.decodeAccumulating(githubData1.asJson.hcursor))


}

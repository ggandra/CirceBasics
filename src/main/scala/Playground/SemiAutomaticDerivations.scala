package Playground

import java.util.UUID
import io.circe._
import io.circe.syntax._
import io.circe.generic.semiauto._

object SemiAutomaticDerivations {

	case class User(firstName: String, lasName: String)

	/*implicit val userDecoder: Decoder[User] = deriveDecoder[User]
	implicit val userEncoder: Encoder[User] = deriveEncoder[User]*/

	implicit val userCodec: Codec[User] = deriveCodec[User]

	User("Gabriel", "Gandra").asJson.spaces2
}

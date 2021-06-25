package TransactionData

import io.circe._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.parser.decode
import io.circe.syntax.EncoderOps

import java.util.UUID

object TransactionCirce extends App {

	case class Transaction(id: UUID, amount: BigDecimal, score: Float, merchant: Merchant)
	object Transaction {
		implicit val decoder: Decoder[Transaction] = deriveDecoder[Transaction]

		implicit val encoder: Encoder[Transaction] = deriveEncoder[Transaction]
	}

	case class Merchant(name: String, lastName: String, mcc: String)
	object Merchant {
		implicit val decoder: Decoder[Merchant] = Decoder.instance { c =>
			for {
				name <- c.downField("nome").as[String]
				lastName <- c.downField("ultimo_nome").as[String]
				mcc <- c.downField("mcc").as[String]
			} yield Merchant(name, lastName, mcc)
		}

		implicit val encoder: Encoder[Merchant] = deriveEncoder[Merchant]
	}

	case class DecodingError()
}

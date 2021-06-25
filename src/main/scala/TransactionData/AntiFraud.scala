package TransactionData

import TransactionData.TransactionCirce.{DecodingError, Transaction}
import io.circe._
import io.circe.syntax.EncoderOps

import java.util.UUID


object AntiFraud extends App {
	val uuid = UUID.randomUUID()

	val transactionData = Json.obj(
		"id" -> uuid.asJson,
		"amount" -> 10000.asJson,
		"score" -> 0.2.asJson,
		"merchant" -> Json.obj(
			"nome" -> "Gabriel".asJson,
			"ultimo_nome" -> "Gandra Gratão".asJson,
			"mcc" -> "8411".asJson
		)
	)

	def dummyAntiFraud(score: Float): Task[] = {
		if (score > 0.7)
	}
//	val transactionDecoded = Transaction.decoder.decodeJson(transactionData).getOrElse(DecodingError)

}

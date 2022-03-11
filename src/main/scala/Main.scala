opaque type JNull = Unit
opaque type JPrimitive = Int | String | Boolean | JNull
type JRec[JArray[_], JObj[_], A] =
  A match
    case JPrimitive =>
      JPrimitive | JArray[JRec[JArray, JObj, JPrimitive]] |
        JObj[JRec[JArray, JObj, JPrimitive]]
    case _ => A | JArray[JRec[JArray, JObj, A]] | JObj[JRec[JArray, JObj, A]]
type JValue = JRec[Seq, [A] =>> Map[String, A], JPrimitive]

val x3: JValue =
  Map("k0" -> 1, "k1" -> Map("k1-1" -> Map("k1-1-1" -> List(false, 1, "2"))))

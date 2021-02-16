package com.karan.nishtharedefined.const

object AppConstants {
    const val SPLASH_HALT : Long = 2500
    const val PERMISSION_SNACK_BAR_HALT = 9000
    const val ENG_FLAG = "en"
    const val HI_FLAG = "hi"
    const val UR_FLAG = "ur"

    const val PRD_REGEX =
        "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[$&.+,:;=?@#|'<>^*()%!-]).{8,64})"

    const val EMAIL_REGEX =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-[\$&+,:;=?@#|'<>-^*()%!]]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})\$"

    const val NUMBER_REGEX = ".*\\d.*"
    const val SPECIAL_CHAR_REGEX = "[\$&+,:;=?@#|'<>^*()%!-]"

    const val MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 1001

}
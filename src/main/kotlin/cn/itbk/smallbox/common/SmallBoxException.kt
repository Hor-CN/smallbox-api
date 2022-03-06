package cn.itbk.smallbox.common

/**
 *
 *@author Thaor
 *@create 2022/3/2 21:04
 */

class SmallBoxException(message: String?) : RuntimeException(message) {

    companion object {
        /**
         * 丢出一个异常
         * @param message
         */
        fun fail(message: String?) {
            throw SmallBoxException(message)
        }
    }

}
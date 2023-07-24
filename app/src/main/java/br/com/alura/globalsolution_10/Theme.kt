package br.com.alura.globalsolution_10

object Theme {
    var currentTheme = R.style.AppTheme2
    private const val ACTUAL = R.style.AppTheme2
    private const val NEW = R.style.AppTheme

    fun switchTheme(){
        Theme.currentTheme = when (Theme.currentTheme){
            ACTUAL -> NEW
            NEW -> ACTUAL
            else -> -1
        }
    }
}
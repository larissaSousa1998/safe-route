package com.example.saferoute.configuration

object SafeRouteConstants {

    // para usar api local, descomente essa
    // no valor, coloque o ip da sua maquina, no seguinte formato http://xxx.xx.xx.x:8080/, onde os 'x' são seu ip
    // para consultar seu ip, abra o cmd, digite ipconfig e veja o 'ipv4' de ethernet ou de wifi
    const val SAFE_ROUTE_API_BASE_URL = "http://10.3.1.110:8080/"


    //para usar api da cloud, descomente essa
    //const val SAFE_ROUTE_API_BASE_URL = "http://18.235.40.112:8080"

    const val VIA_CEP_API_BASE_URL = "https://viacep.com.br/ws/"

}
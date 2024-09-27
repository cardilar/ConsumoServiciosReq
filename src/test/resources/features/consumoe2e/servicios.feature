#language:es
Característica: Como usuario de servicios BackEnd

  @getLista
  Esquema del escenario: : Obtener informacion de los usuarios creados
    Dado Consumo el api con la peticion <peticion> y endpoint <endpoint> y body <body>
    Cuando verifico el estado de la peticion <statusCode>
    Ejemplos:
      | peticion | endpoint         | body | statusCode |
      | get      | api/users?page=2 | n/a  | 200        |

  @PostUsuario
  Esquema del escenario: : Crear usuario
    Dado Consumo el api con la peticion <peticion> y endpoint <endpoint> y body <body>
    Cuando verifico el estado de la peticion <statusCode>
    Entonces valido la respuesta esperada <mensaje>
    Ejemplos:
      | peticion | endpoint  | body                                    | statusCode | mensaje  |
      | post     | api/users | { "name": "morpheus", "job": "leader" } | 201        | morpheus |

  @PutUsuario
  Esquema del escenario: : Actualizar usuario
    Dado Consumo el api con la peticion <peticion> y endpoint <endpoint> y body <body>
    Cuando verifico el estado de la peticion <statusCode>
    Entonces valido la respuesta esperada <mensaje>
    Ejemplos:
      | peticion | endpoint    | body                                        | statusCode | mensaje |
      | put      | api/users/2 | { "name": "luffy", "job": "Qa Automation" } | 200        | luffy   |

  @deleteUsuario
  Esquema del escenario: : eliminar usuario
    Dado Consumo el api con la peticion <peticion> y endpoint <endpoint> y body <body>
    Cuando verifico el estado de la peticion <statusCode>
    Ejemplos:
      | peticion | endpoint    | body | statusCode |
      | get      | api/users/2 | n/a  | 200        |
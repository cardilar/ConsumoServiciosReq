#!groovy

import java.text.SimpleDateFormat

def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()

pipeline {
  agent any
  environment {
    D_WORKSPACE = "D:/jenkinsws"
    D_DIR = "${D_WORKSPACE}/serenity_${timestamp}"
  }

  stages {

    stage('Obtener Fuentes') {

      steps {
        dir(D_WORKSPACE) {
          checkout([$class: 'GitSCM', branches: [
              [name: "master"]
            ],
            doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [
              [credentialsId: "", url: "https://github.com/cardilar/ConsumoServiciosReq.git"]
            ]
          ])
        }
      }
    }


    stage('Ejecutar Pruebas') {
      steps {
        dir(D_WORKSPACE) {
          script {
            try {
              bat("./gradlew clean test")
              echo 'Test Ejecutados sin Fallo'
              currentBuild.result = 'SUCCESS'
            } catch (ex) {
              echo 'Test Ejecutados con Fallo'
              currentBuild.result = 'UNSTABLE'
            }
          }
        }
      }
    }

    stage('Generar evidencia') {
      steps {
        dir(D_WORKSPACE) {
          script {
            try {
              bat " rename \"${D_WORKSPACE}\\target\\site\\serenity\" serenity_${timestamp}"
              echo 'Backup de evidencias realizado con exito'

              publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: "${D_WORKSPACE}//target//site//serenity_${timestamp}",
                reportFiles: 'index.html',
                reportName: 'Evidencias APIs',
                reportTitles: 'APIs'
              ])
              echo 'Reporte Html realizado con exito'
            } catch (e) {
              echo 'No se realizo el Backup de evidencias'
              publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: "${WORKSPACE}//target//site//serenity_${timestamp}", reportFiles: 'index.html', reportName: 'Evidencias Automatizacion APIs', reportTitles: params.tagName])
              echo 'Reporte Html realizado con exito'
              currentBuild.result = 'SUCCESS'
            }
          }
        }
      }
    }
  }
}
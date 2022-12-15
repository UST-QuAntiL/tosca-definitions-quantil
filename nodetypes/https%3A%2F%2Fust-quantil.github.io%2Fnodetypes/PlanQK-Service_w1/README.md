# PlanQK Service [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)  

> This node type deploys a DA as a service on the PlanQK platform.

## Properties

- `ServiceName`: the name the service should have on the PlanQK platform. Must be unique among your services.
- `ServiceDescription`: the description that will be displayed on the PlanQK platform for your service.
- `QuantumBackend`: The quantum backend that should be used. Possible values: `NONE`, `IBM`, `DWAVE`.

## Usage

The DA with the user code needs to be an ArchiveArtifact and the file name needs to be `user_code.zip`.
You have to attach the DA in the Service Template to the Node Template of the PlanQK-Service.
Adding it to the Node Type Implementation **does NOT work**, because it would not get sent to the `create` operation.

Node Templates of the Node Type PlanQK-Service need to be `hosted on` a PlanQK-Platform Node Template.

A Node Template of the Node Type PlanQK-ServiceInstanceInfo needs to be `hosted on` a PlanQK-Service Node Template, otherwise the DA of the PlanQK-Service cannot be deployed.


## Haftungsausschluss

Dies ist ein Forschungsprototyp und enthält insbesondere Beiträge von Studenten.
Diese Software enthält möglicherweise Fehler und funktioniert möglicherweise, insbesondere bei variierten oder neuen Anwendungsfällen, nicht richtig.
Insbesondere beim Produktiveinsatz muss 1. die Funktionsfähigkeit geprüft und 2. die Einhaltung sämtlicher Lizenzen geprüft werden.
Die Haftung für entgangenen Gewinn, Produktionsausfall, Betriebsunterbrechung, entgangene Nutzungen, Verlust von Daten und Informationen, Finanzierungsaufwendungen sowie sonstige Vermögens- und Folgeschäden ist, außer in Fällen von grober Fahrlässigkeit, Vorsatz und Personenschäden ausgeschlossen.

## Disclaimer of Warranty

Unless required by applicable law or agreed to in writing, Licensor provides the Work (and each Contributor
provides its Contributions) on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied, including, without limitation, any warranties or conditions of TITLE, NON-INFRINGEMENT,
MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible for determining the
appropriateness of using or redistributing the Work and assume any risks associated with Your exercise of
permissions under this License.

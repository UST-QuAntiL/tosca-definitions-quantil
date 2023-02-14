# Qiskit Runtime Program [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)  

> This node is used to upload programs to Qiskit Runtime.

## Usage

The DA with the user code needs to a PythonScriptArtifact. It must contains two files:
- `runtime_program.py`: Contains the source code of the Qiskit Runtime program.(see [Qiskit Runtime Tutorial: Construct a Runtime program](https://quantum-computing.ibm.com/lab/docs/iql/runtime/uploading_program#construct-a-runtime-program))
- `meta.json`: Contains the metadata required for upload. (see [Qiskit Runtime Tutorial: Define program metadata](https://quantum-computing.ibm.com/lab/docs/iql/runtime/uploading_program#define-program-metadata))

You have to attach the DA in the Service Template to the Node Template of the QiskitRuntime-Program.
Adding it to the Node Type Implementation **does NOT work**, because it would not get sent to the `create` operation.

Node Templates of the Node Type QiskitRuntime-Program need to be `hosted on` a QiskitRuntime Node Template.


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

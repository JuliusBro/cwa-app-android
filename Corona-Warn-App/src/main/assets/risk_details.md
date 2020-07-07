## Grundlegende Berechnung des Risk Scores
Aus den gesammelten Daten der App berechnet sich der Risk Score wie folgt:
`val riskScore = (maximumRiskScore / normalizationDivisor) * weightedAttenuationDuration`

Wobei 
* `maximumRiskScore` die Zeit mit dem höchsten Risiko ist, aus dem Datensatz, der für diese Berechnung verwendet wird.
* `normalizationDivisor` eine Konstante ist, die bei Division mit dem maximumRiskScore der Einstufung des maximumRiskScores nach die berechnete Zeit noch verändert:
   * Ist das Risiko des maximumRiskScores durchschnittlich, bleibt die berechnete Zeit konstant
   * Ist das Risiko des maximumRiskScores überdurchscnittlich, wird die Zeit mit 1,5 multipliziert
   * Ist das Risiko des maximumRiskScores unter dem Durchschnitt, wird die Zeit auf 1/6 reduziert
* `weightedAttenuationDuration` eine Summe der Zeiten ist, die die Person in niedrigen/mittel/hohen Risikobereichen war, nachdem diese Zeit mit konstanten Gewichten multipliziert wurden:<br>
```
val weightedAttenuationDuration =
            weightedAttenuationLow
                .plus(weightedAttenuationMid)
                .plus(weightedAttenuationHigh)
                .plus(defaultBucketOffset)

val weightedAttenuationLow =
            attenuationParameters.weights.low.capped()
                .times(exposureSummary.attenuationDurationsInMinutes[0])
val weightedAttenuationMid =
            attenuationParameters.weights.mid.capped()
                .times(exposureSummary.attenuationDurationsInMinutes[1])
val weightedAttenuationHigh =
            attenuationParameters.weights.high.capped()
                .times(exposureSummary.attenuationDurationsInMinutes[2])
```
Diesen Code findet man in der Klasse RiskLevelCalculation im package risk.

## Details zum Risk Score
Die Unterscheidung, ob die Zeiten als low, mid oder high gelten, sind abhängig davon, ob die Distanz bei diesen Zeiten zwischen 8-3 Meter(low), 3-1,5 Meter(mid) oder näher als 1,5 Meter(high) waren. Zeiten, die geringer als 10 Minuten sind, werden unabhängig von der Distanz verworfen. Ebenso werden Zeiten verworfen, die mehr als 10 Meter Distanz hatten, unabhängig von der Länge der Zeit.

Es gibt des Weiteren, keine Abstufungen beim Ergebnis: Der Risk Score ist am Ende nichts weiteres, als eine gewichtete Zeit. Liegt diese über 15 Minuten, wird der Benutzer als ein Benutzer mit erhöhtem Risiko eingestuft, er wird benachrichtigt und weitere Handlungen werden ihm nahgelegt.

Diese Informationen finden sich in der offiziellen [Doku](https://github.com/corona-warn-app/cwa-documentation/blob/master/cwa-risk-assessment.md).

Weitere offiziellen Doku der "Transmission risk level" findet sich [hier](https://github.com/corona-warn-app/cwa-documentation/blob/master/transmission_risk.pdf)
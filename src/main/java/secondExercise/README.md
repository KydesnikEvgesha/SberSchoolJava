Задание 2

"Механизм изменения статуса должен быть единым для кадровых документов и не может быть изменен даже при добавлении в программу других видов кадровых документов."

Хочу дать пояснение: 

По моему мнению

у ПРИКАЗов может быть свой статус (СОЗДАН, ИСПОЛНЕН),

у ПИСЕМ может быть свой статус (ОТПРАВЛЕНО, НЕ ОТПРАВЛЕНО)

у иных документов, которые, возможно добавятся, может быть свой статус.

Поэтому в абстрактном классе создал свойство "status" и логика смены статуса происходит через его setter "setStatus" c передачей статуса, но для этого в месте, где необходимо изменить статус вызываю instance of для проверки на необходимый класс и использования правильного enum со статусом




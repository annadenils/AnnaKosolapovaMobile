# AnnaKosolapovaMobile
homework1
Условия для запуска автотестов NativeMobileTests и WebMobileTests (если вы находитесь в EU):
- очистите историю, кэш и куки		
- выключите эмулятор (если он был ранее включен)
- включите vpn, при этом выберите регион UA (или любой не-EU и желательно не-US - там похожие правила)
- запустите автотесты

Дополнительные условия для запуска native - следует в конфигурации перед запуском тестов задать в Enviroment variables параметры:
email; userName; password

homework2
Перед запуском тестов следует получить токен на Mobitru и внести его в конфигурацию maven параметром token
Условия для запуска автотеста NativeCloudMobileTest:
- запустить устройство и установить на него EPAMTestApp.apk (для Android) или EPAMTestApp.li (для iOs)
- внести в xml файл cloudNativeAndroid название устройства в параметр deviceName (для Android)
- внести в xml файл cloudNativeiOs номер устройства в параметр udid (для iOs)
Условия для запуска автотеста WebCloudMobileTests:
- убедиться, что на устройстве установлен браузер Chrome (для Android) Safari (для iOs)
- внести в xml файл cloudWebAndroid название устройства в параметр deviceName (для Android)
- внести в xml файл cloudWebiOs номер устройства в параметр udid (для iOs)
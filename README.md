# cartoons
#Arquitectura mvvm
# se consume una api que regresa el listado de personajes este listado
# se consume el endpoint por medio de Retrofit y se crea el injector de dependencias de Dagger Hilt 
# este pasa a ser consumido en el use case el cual maneja los errores de peticion 
# el use case es consumido  en el viewmodel el cual administra la memoria y hace el procesado de mandar a la vista la informacion necesaria 
# la vista es renderizada en compose 
# se conserva el principio de Patron SOLID para delegar responsabilidades entre las capas de MVVM 
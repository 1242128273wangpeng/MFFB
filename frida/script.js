// 这个方法是为了辅助我输出用的，和python的字符串.format差不多的用法
String.prototype.format = function () {
 var values = arguments;
 return this.replace(/\{(\d+)\}/g, function (match, index) {
 if (values.length > index) {
 return values[index];
 } else {
 return "";
 }
 });
};
​
​
// Resources 类hook
Java.perform(function() {
 var Resources = Java.use('android.content.res.Resources'); // 获取Resources类
 // 因为getString方法重载，有几个我也没数，我只知道我需要用到的是接收一个id作为参数的方法
 //.overload填上你要hook的方法的参数类型，如果不知道直接不写，frida会报错提示你
 Resources['getString'].overload('int').implementation = function(id) { // id是接收到的参数
 console.log('\n----- [Resources.getString] -----');
 var str = this.getText(id); // 这里通过this调用了Resources类中的getText方法
 console.log('resId:{0} => string:{1}'.format(id,str)); // 输出
 return str // 返回
 }
});
​
​
// Toast 类hook，和上面一样
Java.perform(function() {
 var Toast = Java.use('android.widget.Toast');
 Toast['makeText'].overload('android.content.Context', 'java.lang.CharSequence', 'int').implementation = function(context, text, duration) { // 三个参数
 console.log('\n----- [Toast.makeText] -----');
 console.log('[Context]');
 console.log('\n\tContext:', context);
 console.log('\n\tClass:', context.getClass());
 var clazz = String(context.getClass()).split('.');
 console.log('\n\t\tClass Package:', clazz[0]);
 console.log('\n\t\tClass Name:', clazz[1]);
 console.log('Text:', text);
 console.log('Duration:', duration);
 return this.makeText(context, null, text, duration);
 }
});
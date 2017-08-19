		M.js
		=
		
		简介：这是一个轻量级的web前端框架，实现前端脚本的模块化加载，js函数的队列/异步执行，CSS样式注入。
		###她的两个优点：
		- 绝对的轻量级，包含注释255行 压缩之后3.96kb
		- 易于集成到项目和扩展此框架
		
		###框架API调用方法：
		- 队列执行函数
		```javascript
		// M.when();将函数排入队列
		M.when('A',function(){
		    console.log('我是列队A里面的函数');
		});
		M.when('B',function(){
		    console.log('我是列队B里面的函数');
		});
		// M.trigger();执行队列里面的函数
		M.trigger('B').trigger('A');//函数执行 B函数 A函数
		```
		- 外部脚本加载，也叫模块化加载，有一种装逼的叫法叫异步加载。
		```javascript
		// M.add();装载模块 里面有两个参数 1、模块名称 2、对象字面量{path:'文件路径',type:'文件类型',requires:'定义依赖关系'}。M.run();模块加载的运行方法。
		M.add('b',{path:'js/m/a.css',type:'css'});
		M.add('a',{path:'js/m/a.js',type:'js',requires:['b']});
		M.run('a',function(){
		// 这里是回调函数
		});
		```
		- 模块批量加载
		```javasript
		M.set({
			a:{path:'',type:''},
			b:{path:'',type:'',requires:['a']},
			c:{path:'',type:'',requires:['b']},
			d:{path:'',type:'',requires:['c']}
		});
		M.run('d',function(){});
		```
		- CSS样式注入
		```javascript
		// 两个参数 第一个参数是标示符 必须唯一，第二个是注入的内容
		M.css('a',[
		        '.a{margin:0;}',
		        '.b{text-decoration:none;}'
		    ].join('\n')
		);
		// 也可以这样
		M.css('b',.a{margin:0;}');
		```
		- 关闭自动加载
		```html
		<script type="text/javascript" src="./js/M.js" auto="false"></script>
		```
		- 重新指定核心库
		```html
		<script type="text/javascript" src="./js/M.js" core="./js/sizzle.js"></script>
		```
/* --------------------------------------------------
 * 名称：M.js框架
 * 描述：这是一个轻量级的脚本文件模块化加载框架
 * 主要功能：脚本文件模块化加载 函数队列执行 样式注入
 * 兼容性：几乎兼容所有现代浏览器
 * 性能：尚未测试 但能保证她绝对不是最慢的
 * 开发者：单骑闯天下
 * 最后更新时间：2014.3.13
 * 版本：v 1.0.1
 * ---------------------- 项目历程 ------------------
 * # v1.0.0
 * 2013年12月份开始构思js模块化加载
 * 2014年3月11框架发布并且定义名称为M.js
 * # v1.0.1
 * 2014年3月12日修正css注入的bug
 * ------------------------------------------------*/
;({
    /* 核心库的配置文件 */
    config:{
        auto:true,
        coreLib:['../M/jquery.1.9.1.min.js'],
        model:{}
    },
    uid:0, /* 控制核心库的加载次数 避免无谓的计算 */
    count:0,/* 队列执行需要用到的计数 */
    map:{},/* 队列执行的字面量 */
    rmap:{},/* 队列执行的字面量 */
    jsReference:{},/* 加载外部模块参考位置 */
    reference:{},/* 指定的位置 */
    cssArr:{},/* 存储已注入css的索引 */
    loaded:{},/* 加载完成 readyState */
    loadList:{},/* 函数执行完毕 开始准备执行回调函数 */
    version:'1.0.1',
    /* 负责初始化 */
    init:function(){
        var that=this;
        /* 初始外部配置 */
        (function(){
            var jsObj = (function (a) {
                var files = a.getElementsByTagName('script'),
                    n=parseInt( (files.length - 1),10);
                that.jsReference[0] = files[n];
                return files[n];
            }(document));
            that.reference[0]=document.body.firstChild;
            (function(a){
                var obj=a,
                    initAuto=obj.getAttribute('auto'),/* 是否关闭加载核心库 */
                    initCore=obj.getAttribute('core');/* 外部加载的核心库 */
                if(initAuto){
                    that.config.auto=(initAuto.toLowerCase()==='false')?false:true;
                }
                if(initCore){
                    that.config.coreLib=initCore.split(',');
                }
            }(jsObj||{}));
        }());
        window.M=(function(){
            return that.method();
        }());
    },
    getMod:function (e){
        var model = this.config.model,
            mod; 
        if(typeof e === 'string') {
            mod=(model[e]) ? model[e] : {path : e};
        }else{
            mod = e;
        }
        return mod;
    },
    load:function(path,type,charset,fn){
        var node,
            t,
            that=this,
            done=function(){
                that.loaded[path]=1;
                fn();
            };
        if(typeof path ==='string'){
            t=type || path.toLowerCase().split(/\./).pop().replace(/[\?#].*/,'');
        }else{
            path=path.join("");
            t=type || path.toLowerCase().split(/\./).pop().replace(/[\?#].*/,'');
        }
        if(t==='js'){
            node=document.createElement('script');
            node.setAttribute('src',path);
            node.setAttribute('async',true);
        }else if(t==='css'){
            node=document.createElement('link');
            node.setAttribute('href', path);
            node.setAttribute('type', 'text/css');
            node.setAttribute('rel', 'stylesheet');
        }
        if(charset){node.charset=charset;}
        node.onerror=function(){done();node.onerror=null;};
        node.onload=node.onreadystatechange=function(){
            if(!this.readyState || this.readyState === 'loaded' || this.readyState === 'complete'){
                done();
                node.onload=node.onreadystatechange=null;
            }
        };
        that.jsReference[0].parentNode.insertBefore(node,that.jsReference[0]);
    },
    /* 判断是否加载依赖文件或者独立的模块 在这里并不执行任何实质性的加载而是执行递归判断文件依赖性然后调用load进行加载 */
    check:function(deps,cb){
        var name=deps.join(''),
            mod=this.getMod(name),
            that=this,
            path=mod.path;
        /* 它是被load fn 所引用 */
        var callback=function (){
                that.loadList[name]=1;/* 代表已经加载完毕了 函数执行完毕 开始准备执行回调函数 */
                cb();
            };
        /* 比如run依赖h执行 在框架内部会开始加载run 也就是返回函数 然后执行h */
        if(mod.requires){
            this.check(mod.requires,(
                /* 只有这里执行完毕之后外部的递归才会开始执行 为什么要用return 因为return会让后面的语句计算 才能保证依赖执行 */
                function(m){
                    return function(){
                        that.load(m.path,m.type,m.charset,callback);
                    };
                }(mod))
            );
        }else{
            this.load(mod.path,mod.type,mod.charset,callback);
        }
    },
    /* 查找数组里面当前元素的位置 */
    indexOf:Array.prototype.indexOf || function(obj){
        for(var i=0,len=this.length;i<len;++i){
            if(this[i]===obj){return i;}
        }
        return -1;
    },
    /* 异步执行的内置方法 */
    release:function(res,list){
        var maps=this.map,
            rmaps=this.rmap,
            /* 某些方面讲这是个异步执行的核心方法 这方法还有优化空间 */
            fire=function(callback,thisObj){
                setTimeout(function(){
                    callback.call(thisObj);
                },0);
            };
        for(var i=0,len=list.length;i<len;++i){
            var uid=list[i],
                mapItem=maps[uid],
                waiting=mapItem.waiting,
                pos=this.indexOf.call(waiting,res);
                waiting.splice(pos,1);
            if (waiting.length===0){
                fire(mapItem.callback,mapItem.thisObj);
                delete maps[uid];
            }
        }
    },
    /* 提供给外部访问的方法接口 */
    method:function(){
        var that=this;
        return {
            /* 运行方法 */
            run:function(){
                var args=[].slice.call(arguments),
                    fn,
                    id,
                    len=parseInt(args.length,10);
                that.uid++;
                /* 加载核心库 */
                if(that.config.auto && ( that.loadList[that.config.coreLib.join('')] !== true ) && ( (that.uid>1) !== true ) ){
                    that.check(that.config.coreLib,function(){
                        M.run.apply(that,args);
                    });
                    return M;
                }
                if( (len>0) && ( (that.loadList[args[0]]===1) !== true ) ){
                    if(typeof args[len-1]==='function'){
                        fn=args.pop();
                    }
                    id=args.join('');
                    if((args.length === 0 || that.loadList[id]) && fn){
                        fn();
                        return M;
                    }
                    /* 正常加载 */
                    that.check(args,function(){
                        that.loadList[id] = 1;
                        if(fn){
                            fn();
                        }
                    });
                }else{
                    return M;
                }
                return M;
            },
            /* 批量设置模块的方法 */
            set:function(m){
                if(m){
                    for(var a in m){
                        that.config.model[a]=m[a];
                    }
                }
                return M;
            },
            /* CSS注入方法 */
            css:function(a,s){
                var css=document.getElementById('addCss');
                if(!css){
                    css=document.createElement('style');
                    css.type='text/css';
                    css.id='addCss';
                    that.reference[0].parentNode.insertBefore(css,that.reference[0]);
                }
                if( (that.cssArr[0]===a) !== true ){
                    that.cssArr[0]=a;
                    if(css.styleSheet){
                        css.styleSheet.cssText=css.styleSheet.cssText + s;
                    }else{
                        css.appendChild(document.createTextNode(s));
                    }
                }
                return M;
            },
            /* 单个添加模块的方法 */
            add:function(name,obj){
                if(!name || !obj || !obj.path){return;}
                that.config.model[name]=obj;
                return M;
            },
            /* 异步执行外部调用的等待方法 */
            when:function(resources, callback, thisObj){
                var maps=that.map,
                    rmaps=that.rmap;
                if(typeof resources === 'string'){resources=[resources];}
                var id=(that.count++).toString(16);
                maps[id]={
                    waiting:resources.slice(0),
                    callback:callback,
                    thisObj:thisObj || window
                };
                for (var i=0,len=resources.length;i<len;++i){
                    var res=resources[i],
                        list=rmaps[res] || (rmaps[res]=[]);
                    list.push(id);
                }
                return M;
            },
            /* 异步执行外部调用的触发方法 */
            trigger:function(resources){
                if(!resources){return M;}
                var maps=that.map,
                    rmaps=that.rmap;
                if(typeof resources==='string'){resources=[resources];}
                for(var i=0,len=resources.length;i<len;++i){
                    var res=resources[i];
                    if (typeof rmaps[res]==='undefined') continue;
                    that.release(res,rmaps[res]);
                    delete rmaps[res];
                }
                return M;
            }
        };
    }
}).init();
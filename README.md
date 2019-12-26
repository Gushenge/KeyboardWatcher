# KeyboardWatcher

最完善的键盘弹出和键盘隐藏监听处理。相对于原库进行了修改
迁移至AndroidX,适配Android10,用kotlin重写了

## 源库地址
    https://github.com/18511084155/KeyboardWatcher

## Gradle 引用

    [项目已合并至ATools,使用方式不变,点击查看](https://github.com/Gushenge/Atools)

### 示例代码

```
    private lateinit var keyboardWatcher: KeyboardWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        keyboardWatcher = get().init(this, window.decorView){b,i->
            if (b) {
                Toast.makeText(application, "键盘显示了！高度差：$i", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(application, "键盘隐藏了！高度差：$i", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        keyboardWatcher.remove()
    }
```


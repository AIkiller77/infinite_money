package com.a.a.b;

public class i extends h {
    private static h f;
    private static final String[] g = "and|break|case|continue|default|do|else|elseif|end|false|for|function|goto|if|in|local|nil|not|or|repeat|return|switch|then|true|until|while".split("\\|");
    private static final String[] h = "__add|__band|__bnot|__bor|__bxor|__call|__concat|__div|__eq|__idiv|__index|__le|__len|__lt|__mod|__mul|__newindex|__pow|__shl|__shr|__sub|__tostring|__unm|_ENV|_G|assert|collectgarbage|dofile|error|findtable|getmetatable|ipairs|load|loadfile|loadstring|module|next|pairs|pcall|print|rawequal|rawget|rawlen|rawset|require|select|self|setmetatable|tointeger|tonumber|tostring|type|unpack|xpcall|activity|call|compile|dump|each|enum|import|loadbitmap|loadlayout|loadmenu|service|set|task|thread|timer|coroutine|debug|io|luajava|math|os|package|string|table|utf8".split("\\|");
    private static final char[] i = {'(', ')', '{', '}', ',', ';', '=', '+', '-', '/', '*', '&', '!', '|', ':', '[', ']', '<', '>', '?', '~', '%', '^'};

    private i() {
        super.a(i);
        super.a(g);
        super.b(h);
        super.a("io", "close|flush|input|lines|open|output|popen|read|stderr|stdin|stdout|tmpfile|type|write".split("\\|"));
        super.a("string", "byte|char|dump|find|format|gfind|gmatch|gsub|len|lower|match|pack|packsize|rep|reverse|sub|unpack|upper".split("\\|"));
        super.a("luajava", "astable|bindClass|clear|coding|createArray|createProxy|getContext|instanceof|loadLib|loaded|luapath|new|newInstance|package|tostring".split("\\|"));
        super.a("os", "clock|date|difftime|execute|exit|getenv|remove|rename|setlocale|time|tmpname".split("\\|"));
        super.a("table", "concat|foreach|foreachi|insert|maxn|move|pack|remove|sort|unpack".split("\\|"));
        super.a("math", "abs|acos|asin|atan|atan2|ceil|cos|cosh|deg|exp|floor|fmod|frexp|huge|ldexp|log|log10|max|maxinteger|min|mininteger|modf|pi|pow|rad|random|randomseed|sin|sinh|sqrt|tan|tanh|tointeger|type|ult".split("\\|"));
        super.a("utf8", "byte|char|charpattern|charpos|codepoint|codes|escape|find|fold|gmatch|gsub|insert|len|lower|match|ncasecmp|next|offset|remove|reverse|sub|title|upper|width|widthindex".split("\\|"));
        super.a("coroutine", "create|isyieldable|resume|running|status|wrap|yield".split("\\|"));
        super.a("package", "config|cpath|loaded|loaders|loadlib|path|preload|searchers|searchpath|seeall".split("\\|"));
        super.a("debug", "debug|gethook|getinfo|getlocal|getmetatable|getregistry|getupvalue|getuservalue|sethook|setlocal|setmetatable|setupvalue|setuservalue|traceback|upvalueid|upvaluejoin".split("\\|"));
    }

    public static h g() {
        if (f == null) {
            f = new i();
        }
        return f;
    }

    public boolean a(char c, char c2) {
        return c == '-' && c2 == '-';
    }

    public boolean b(char c, char c2) {
        return c == '[' && c2 == '[';
    }

    public boolean c(char c, char c2) {
        return c == ']' && c2 == ']';
    }

    public boolean h(char c) {
        return false;
    }
}

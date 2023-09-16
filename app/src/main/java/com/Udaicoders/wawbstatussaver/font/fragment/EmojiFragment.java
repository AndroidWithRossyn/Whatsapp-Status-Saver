package com.Udaicoders.wawbstatussaver.font.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.font.adapter.EmojiAdapter;

import java.util.ArrayList;

public class EmojiFragment extends Fragment {

    private Activity context;
    private ArrayList<String> emoticonFonts = new ArrayList<>();
    private RecyclerView fontsRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_emoji, container, false);

        if (emoticonFonts.isEmpty()) {

            emoticonFonts.add("★~(◠‿◕✿)");
            emoticonFonts.add("乂❤‿❤乂");
            emoticonFonts.add("༼♥ل͜♥༽");
            emoticonFonts.add("ლζ*♡ε♡*ζლ");
            emoticonFonts.add("⊂（❤⌂❤）⊃");
            emoticonFonts.add("٩(*❛⊰❛)ʓਡ～❤");
            emoticonFonts.add("♥╣[-_-]╠♥");
            emoticonFonts.add("( ･_･)♡");
            emoticonFonts.add("(●'◡'●)ﾉ♥");
            emoticonFonts.add("(* ˘⌣˘)◞[_]❤[_]ヽ(•‿• )");
            emoticonFonts.add("( ＾◡＾)っ✂❤");
            emoticonFonts.add("(Ɔ ˘⌣˘)♥(˘⌣˘ C) ");
            emoticonFonts.add("(乂ღ˘⌣˘)ノ❤ヽ(ˆ⌣ˆ)ヾ");
            emoticonFonts.add("(ღ˘⌣˘)♥ ℒ♡ⓥℯ ㄚ♡ⓤ");
            emoticonFonts.add("▂▃▄▅▆▇█▓▒░LoveYou░▒▓█▇▆▅▄▃▂");
            emoticonFonts.add("(。♡‿♡。)");
            emoticonFonts.add("♡o。.(✿ฺ。✿ฺ)");
            emoticonFonts.add("♡＾▽＾♡");
            emoticonFonts.add("(ღ˘⌣˘ღ)");
            emoticonFonts.add("웃❤유");
            emoticonFonts.add("{❤‿❤}");
            emoticonFonts.add("[̲̅ə̲̅٨̲̅٥̲̅٦̲̅]");
            emoticonFonts.add("┣┓웃┏♨❤♨┑유┏┥");
            emoticonFonts.add("(Ɔ ˘⌣˘)˘⌣˘ C) ");
            emoticonFonts.add("✨Lᵒᵛᵉᵧₒᵤ");


            emoticonFonts.add("✿◕ ‿ ◕✿");
            emoticonFonts.add("೭੧(❛▿❛✿)੭೨");
            emoticonFonts.add("◎[▪‿▪]◎");
            emoticonFonts.add("☆(❁‿❁)☆");
            emoticonFonts.add("(✿◠‿◠) ");
            emoticonFonts.add("°˖✧◝(⁰▿⁰)◜✧˖°");
            emoticonFonts.add("≧◉◡◉≦");
            emoticonFonts.add("≧◠‿◠≦");
            emoticonFonts.add("٩(˘◡˘)۶");
            emoticonFonts.add("(✿ꈍ。 ꈍ✿)");
            emoticonFonts.add("( ๑ ᴖ ᴈ ᴖ)ᴖ ᴑ ᴖ๑)❣");
            emoticonFonts.add("( ͡° ͜ʖ ͡°)");
            emoticonFonts.add("┑(￣▽￣)┍");
            emoticonFonts.add("(❁´◡`❁)");
            emoticonFonts.add("(●⌒∇⌒●)");
            emoticonFonts.add("ಥ‿ಥ");
            emoticonFonts.add("(◕‿◕)");
            emoticonFonts.add("≖‿≖");
            emoticonFonts.add("(◠‿◠)");
            emoticonFonts.add("（⌒▽⌒）");
            emoticonFonts.add("(•ิ‿•ิ)");
            emoticonFonts.add("ʘ‿ʘ");
            emoticonFonts.add("o(∩_∩)o");
            emoticonFonts.add("ლ(╹◡╹ლ)");



            emoticonFonts.add("♪(๑ᴖ◡ᴖ๑)♪");
            emoticonFonts.add("|̲̅̅●̲̅̅|̲̅̅=̲̅̅|̲̅̅●̲̅̅|");
            emoticonFonts.add("♫♪.ılılıll|̲̅̅●̲̅̅|̲̅̅=̲̅̅|̲̅̅●̲̅̅|llılılı.♫♪");
            emoticonFonts.add("Ϛ⃘๑•͡ .̫•๑꒜♬♫");
            emoticonFonts.add("(ღ˘⌣˘ღ) ♫･*:.｡. .｡.:*･");
            emoticonFonts.add("♪♪ｖ(⌒ｏ⌒)ｖ♪♪");
            emoticonFonts.add("♬♫♪◖(●。●)◗♪♫♬");
            emoticonFonts.add("♪٩(✿′ᗜ‵✿)۶♪");
            emoticonFonts.add("♬♩♫♪☻(●´∀｀●)☻♪♫♩♬");
            emoticonFonts.add("♬♪♫ ヾ(*・。・)ﾉ ♬♪♫");
            emoticonFonts.add("٩(ó｡ò۶ ♡)))♬");
            emoticonFonts.add("♪~♪ d(⌒o⌒)b♪~♪");
            emoticonFonts.add("♪♪(o*゜∇゜)o～♪♪");
            emoticonFonts.add("✌♫♪˙❤‿❤˙♫♪✌");
            emoticonFonts.add("(◦′ᆺ‵◦) ♬° ✧❥✧¸.•*¨*✧♡✧ ℒℴѵℯ ✧♡✧*¨*•.❥");
            emoticonFonts.add("✿♬ﾟ+.(｡◡‿◡)♪.+ﾟ♬✿。");
            emoticonFonts.add("♪ヽ( ⌒o⌒)人(⌒-⌒ )v ♪");
            emoticonFonts.add("ヽ(⌐■_■)ノ♪♬");


            emoticonFonts.add("(<●>ω<●>)✧");
            emoticonFonts.add("ฅ( ̳͒•ಲ• ̳͒)♪");
            emoticonFonts.add("ʕ •ᴥ•ʔ");
            emoticonFonts.add("ʕ•̫͡•ʔ❤ʕ•̫͡•ʔ");
            emoticonFonts.add("ʕつ ͡◔ ᴥ ͡◔ʔつ");
            emoticonFonts.add("U(⁎˃ᆺ˂)U");
            emoticonFonts.add("U(ㅇㅅㅇ❀)U");
            emoticonFonts.add("₍ᐢ•ﻌ•ᐢ₎*･ﾟ｡");
            emoticonFonts.add("₍˄·͈༝·͈˄*₎◞ ̑̑❤️くコ:≡");
            emoticonFonts.add("(•͈⌔•͈⑅)");
            emoticonFonts.add("╰༼ ∗ ಡ ▾ ಡ ∗ ༽╯");
            emoticonFonts.add("✿♬ﾟ+.(｡◡‿◡)♪.+ﾟ♬✿。");

            emoticonFonts.add("凸ಠ益ಠ)凸");
            emoticonFonts.add("( ︶︿︶)_╭∩╮");
            emoticonFonts.add("┌∩┐(◣_◢)┌∩┐");
            emoticonFonts.add("ᕕ༼ ͠ຈ Ĺ̯ ͠ຈ ༽┌∩┐");
            emoticonFonts.add("ᕕ(˵•̀෴•́˵)ᕗ");
            emoticonFonts.add("{{{(>_<)}}}");
            emoticonFonts.add("ᕙ(⇀‸↼‶)ᕗ");


            emoticonFonts.add("┏༼ ◉ ╭╮ ◉༽┓");
            emoticonFonts.add("(๑◕︵◕๑)");
            emoticonFonts.add("(︶︹︺)");
            emoticonFonts.add("(´°̥̥̥̥̥̥̥̥ω°̥̥̥̥̥̥̥̥｀)");
            emoticonFonts.add("╯﹏╰");
            emoticonFonts.add("~(｡☉︵ ಠ@)>");
            emoticonFonts.add("(▰︶︹︺▰)");
            emoticonFonts.add("( ⁍᷄⌢̻⁍᷅ )");
            emoticonFonts.add("‧º·(˚ ˃̣̣̥⌓˂̣̣̥ )‧º·˚");
            emoticonFonts.add("(╯︵╰,)");
            emoticonFonts.add("(இ﹏இ`｡)");

            emoticonFonts.add("(-, – )…zzzZZZ");
            emoticonFonts.add("✾꒡ .̮ ꒡✾");
            emoticonFonts.add("( ु⁎ᴗ_ᴗ⁎)ु.｡oO");
            emoticonFonts.add("(ृ ु⁎ᴗᵨᴗ⁎)ु.zZ");
            emoticonFonts.add("꒰ ꒡⌓꒡꒱ᏩɵɵᎴ ɳɩɠɧ✟");
            emoticonFonts.add("【☆sweet dream☆】(●ＵωU).zZZ");

            emoticonFonts.add("(((o(*ﾟ▽ﾟ*)o)))");
            emoticonFonts.add("o((*^▽^*))o");
            emoticonFonts.add("⌒°(❛ᴗ❛)°⌒");
            emoticonFonts.add("(۶ꈨຶꎁꈨຶ )۶ʸᵉᵃʰᵎ");
            emoticonFonts.add("୧༼✿ ͡◕ д ◕͡ ༽୨");
            emoticonFonts.add("✧⁺⸜(●′▾‵●)⸝⁺✧");
            emoticonFonts.add("ヾ(o✪‿✪o)ｼ");

            emoticonFonts.add("(๑′ڡ‵๑)۶४४yϋᵐᵐӵ♡॰⋆̥");
            emoticonFonts.add("╰། ❛ ڡ ❛ །╯");
            emoticonFonts.add(" ԅ| . ͡° ڡ ͡° . |ᕤ");
            emoticonFonts.add("( ๑ ❛ ڡ ❛ ๑ )❤");
            emoticonFonts.add("(✽´ཫ`✽)");
            emoticonFonts.add("ʕっ˘ڡ˘ʔっ─∈");

            emoticonFonts.add("ᕕ╏ ͡ ▾ ͡ ╏┐");
            emoticonFonts.add("(ง ´͈౪`͈)ว");
            emoticonFonts.add("(#^.^#)");
            emoticonFonts.add(" (๑•́‧̫•̀๑)");
            emoticonFonts.add("(๑￫‿￩๑)");
            emoticonFonts.add("(✿ꈍ。 ꈍ✿)");
            emoticonFonts.add("(∗ᵕ̴᷄◡ᵕ̴᷅∗)՞");

            emoticonFonts.add("☮▁▂▃▄☾ ♛ ◡ ♛ ☽▄▃▂▁☮");
            emoticonFonts.add(".₊̣̇.ෆ˟̑*̑˚̑*̑˟̑ෆ.₊̣̇.ෆ˟̑*̑˚̑*̑˟̑ෆ.₊̣̇.ෆ˟̑*̑˚̑*̑˟̑ෆ.₊̣̇.ෆ˟̑*̑˚̑*̑˟̑ෆ.₊̣̇.");
            emoticonFonts.add("♡*+:•*∴”:♡.•♬✧♡*+:•*∴”:♡.•♬✧");
            emoticonFonts.add("* ੈ✩‧₊˚* ੈ✩‧₊˚* ੈ✩‧₊˚");
            emoticonFonts.add("☆彡★彡☆彡★彡☆彡★彡☆彡★彡");
            emoticonFonts.add("♡հҽӀӀօ♡* ૂི•̮͡• ૂ ྀෆ⃛﹡೫٭ॢ*⋆♡⁎೨ ♡⃛ෆ͙⃛ ॢ٭ॢ*⋆♡⁎೨");
            emoticonFonts.add("★☆。.:*:･”ﾟ★βyёヾ(⌒∇⌒)ﾉβyё★｡.:*:･”☆★");
            emoticonFonts.add("㇏( ෆั ⌣ ෆั )ﾉցօօժ ʍօɾղíղց❣");
            emoticonFonts.add("☻⋆˚✩Ꮹ∞ძ ოǫɾлілϧ ༘*ೄ˚☻");
            emoticonFonts.add(".｡.:*･ﾟ☆ｓωεετ*･ﾟｄｒεαｍ☆.｡.:*･ﾟ”￡(｡･”･)-†");
            emoticonFonts.add("ヽ（≧ω≦）｛☆HAPPY★BIRTHDAY☆｝（≧ω≦）/");
            emoticonFonts.add("(ෆˊ͈ ु꒳ ूˋ͈ෆ) ˡºᵛᵉ❤⃛");
            emoticonFonts.add("✿*ﾟ¨ﾟ✎･ ✿.｡.:* *.:｡✿*ﾟ¨ﾟ✎･✿.｡.:* ♡LOVE♡LOVE♡ ✿*ﾟ¨ﾟ✎･ ✿.｡.:*");
            emoticonFonts.add("ෆ⃛ෆ⃛ෆ⃛ ♡♡[τ̲̅н̲̅a̲̅и̲̅κ̲̅ ч̲̅o̲̅u̲̅]ᴗ͈ₒᴗ͈♡");
            emoticonFonts.add("♬°⋆ɱUꑄյ͛ʗ⋆°♬");
            emoticonFonts.add("■■■■■■■■■■■□□□ NOWLOADING");







        } else {
            emoticonFonts.clear();
            emoticonFonts.add("✿◕ ‿ ◕✿");
            emoticonFonts.add("◎[▪‿▪]◎");
            emoticonFonts.add("☆(❁‿❁)☆");
            emoticonFonts.add("(✿◠‿◠) ");
            emoticonFonts.add("⊂◉‿◉つ");
            emoticonFonts.add("≧◉◡◉≦");
            emoticonFonts.add("≧◠‿◠≦");
            emoticonFonts.add("٩(˘◡˘)۶");
            emoticonFonts.add("(^)o(^)");
            emoticonFonts.add("（*^_^*）");
            emoticonFonts.add("( ͡° ͜ʖ ͡°)");
            emoticonFonts.add("┑(￣▽￣)┍");
            emoticonFonts.add("(❁´◡`❁)");
            emoticonFonts.add("(●⌒∇⌒●)");
            emoticonFonts.add("ಥ‿ಥ");
            emoticonFonts.add("(◕‿◕)");
            emoticonFonts.add("≖‿≖");
            emoticonFonts.add("(◠‿◠)");
            emoticonFonts.add("（⌒▽⌒）");
            emoticonFonts.add("(•ิ‿•ิ)");
            emoticonFonts.add("ʘ‿ʘ");
            emoticonFonts.add("o(∩_∩)o");
            emoticonFonts.add("ლ(╹◡╹ლ)");
            emoticonFonts.add("★~(◠‿◕✿)");
            emoticonFonts.add("♥‿♥");
            emoticonFonts.add("♥╣[-_-]╠♥");
            emoticonFonts.add("( ･_･)♡");
            emoticonFonts.add("★~(◡﹏◕✿)");

        }

        fontsRV = view.findViewById(R.id.recycle_view_FF);

        final EmojiAdapter adapter = new EmojiAdapter(emoticonFonts, context);
        fontsRV.setLayoutManager(new LinearLayoutManager(context));
        fontsRV.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
    }


}
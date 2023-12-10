import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import com.example.foodapp.R
import com.example.foodapp.fragment.AboutMeFragment
import com.example.foodapp.fragment.BlogFragment
import com.example.foodapp.fragment.ContactFragment
import com.example.foodapp.fragment.MealPlannerFragment

class PageAdapter(fm: FragmentManager, private val context: Context) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabTitles = arrayOf("Recipes", "Meal Planner", "Blog", "Contact", "About Me")
    private val tabIcons = arrayOf(
        R.drawable.ic_baseline_fastfood_24,
        R.drawable.ic_baseline_edit_note_24,
        R.drawable.ic_baseline_menu_book_24,
        R.drawable.ic_baseline_contact_page_24,
        R.drawable.ic_baseline_person_24
    )

    override fun getCount(): Int = tabTitles.size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> RecipesFragment()
            1 -> MealPlannerFragment()
            2 -> BlogFragment()
            3 -> ContactFragment()
            4 -> AboutMeFragment()
            else -> RecipesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }

    fun getTabIcon(position: Int): Drawable? {
        return ContextCompat.getDrawable(context, tabIcons[position])
    }

    fun getTabTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}

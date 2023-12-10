import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
//import com.example.foodapp.Recipe
import com.example.foodapp.adapter.RecipesAdapter
//import com.example.foodapp.fragment.RecipeDetailBottomSheetFragment
import com.example.foodapp.sharedpreference.RecipeStorage
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecipesFragment : Fragment() {


    private lateinit var recipeAdapter: RecipesAdapter
    private val recipesList = mutableListOf<Recipe>()
    private lateinit var recipeSharedPreferences: RecipeStorage
    private val PICK_IMAGE_REQUEST_CODE = 1001
    private lateinit var imageViewRecipe: ImageView
    private var selectedImageUri: Uri? = null
        private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)
        recipeSharedPreferences = RecipeStorage(requireContext())

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewRecipes)
        recipeAdapter = RecipesAdapter(recipesList)



        recyclerView.adapter = recipeAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val fabAddMealPlan = view.findViewById<FloatingActionButton>(R.id.addNewReceipe)
        fabAddMealPlan.setOnClickListener {
            showAddRecipeDialog()
        }

        loadRecipesFromSharedPreferences()

        return view
    }

//    private fun setupRecyclerView() {
//        recipeAdapter = RecipesAdapter(recipesList) { recipe ->
//            val bottomSheetFragment = RecipeDetailBottomSheetFragment.newInstance(recipe)
//            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
////        }
//        recyclerView.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = recipeAdapter
////        }
//    }}}





        private fun showAddRecipeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_recipe, null)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add New Recipe")

        val recipeNameEditText = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val cookingTimeEditText = dialogView.findViewById<EditText>(R.id.editTextCookingTime)
        val discriptionEditText = dialogView.findViewById<EditText>(R.id.editTextDescription)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.ratingBar)
        val buttonSelectImage = dialogView.findViewById<Button>(R.id.selectImage)
        imageViewRecipe = dialogView.findViewById(R.id.imageViewRecipe)

        buttonSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        }

        dialogBuilder.setPositiveButton("Add") { dialog, _ ->
            val recipeName = recipeNameEditText.text.toString()
            val cookingTime = cookingTimeEditText.text.toString()
            val discription = discriptionEditText.text.toString()
            val rating = ratingBar.rating

            val newRecipe = Recipe(recipeName, cookingTime, discription,  selectedImageUri.toString() ,rating)
            recipesList.add(newRecipe)
            saveRecipes()
            recipeAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun saveRecipes() {
        recipeSharedPreferences.saveRecipes(recipesList)
    }

    private fun loadRecipesFromSharedPreferences() {
        recipesList.clear()
        recipesList.addAll(recipeSharedPreferences.getRecipes())
        recipeAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri? = data?.data
            if (::imageViewRecipe.isInitialized) {
                imageViewRecipe.setImageURI(selectedImage)
                selectedImageUri = selectedImage
            }
        }
    }

//
//
//





}
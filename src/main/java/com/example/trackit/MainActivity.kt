package com.example.trackit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackit.data.Expense
import com.example.trackit.screens.BudgetScreen
import com.example.trackit.ui.theme.TrackItTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BudgetScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExpenseTrackerApp() {
    val expenseList = remember {
        mutableStateOf(listOf(
            Expense("Groceries", "$50"),
            Expense("Dining", "$30"),
            Expense("Transportation", "$20"),
            Expense("Entertainment", "$40"),
        ))
    }
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Expense Tracker") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                SearchBar(searchQuery = searchQuery.value, onQueryChange = { searchQuery.value = it })
                ExpenseList(expenses = expenseList.value)
            }
        }
    )
}

@Composable
fun SearchBar(searchQuery: String, onQueryChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchQuery,
        onValueChange = onQueryChange,
        label = { Text("Search expenses") }
    )
}

@Composable
fun ExpenseList(expenses: List<Expense>) {
    Column {
        expenses.forEach { expense ->
            ExpenseItem(expense = expense)
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f))
        }
    }
}

@Composable
fun ExpenseItem(expense: Expense) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = expense.category, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = expense.amount, style = MaterialTheme.typography.body1)
    }
}

@Preview
@Composable
fun PreviewExpenseTrackerApp() {
    ExpenseTrackerApp()
}







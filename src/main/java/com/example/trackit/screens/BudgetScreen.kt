package com.example.trackit.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackit.data.Budget

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BudgetScreen() {
    val budgetList = listOf(
        Budget("Groceries", "$200"),
        Budget("Dining", "$100"),
        Budget("Transportation", "$50"),
        Budget("Entertainment", "$150"),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Budget Tracker") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Budgets",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                BudgetList(budgets = budgetList)
            }
        }
    )
}

@Composable
fun BudgetList(budgets: List<Budget>) {
    Column {
        budgets.forEach { budget ->
            BudgetItem(budget = budget)
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f))
        }
    }
}

@Composable
fun BudgetItem(budget: Budget) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = budget.category, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = budget.limit, style = MaterialTheme.typography.body1)
    }
}

@Preview
@Composable
fun PreviewBudgetScreen() {
    BudgetScreen()
}


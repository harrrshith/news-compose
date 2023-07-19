package com.harshith.news.ui.interests

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harshith.news.R
import com.harshith.news.data.Result
import com.harshith.news.data.interests.InterestsSection
import com.harshith.news.data.interests.TopicSelection
import com.harshith.news.data.interests.impl.FakeInterestsRepository
import com.harshith.news.ui.theme.NewsTheme
import kotlinx.coroutines.runBlocking

enum class Sections(@StringRes val titleId: Int){
    Topic(R.string.topics),
    People(R.string.people),
    Publication(R.string.publications)
}

class TabContent(
    val section: Sections,
    val content: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterestsScreen(
    tabContent: List<TabContent>,
    currentSection: Sections,
    isExpandedScreen: Boolean,
    onTabChange: (Sections) -> Unit,
    openDrawer: () ->Unit,
    snackbarHostState: SnackbarHostState
){
    val context = LocalContext.current
    Scaffold(
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)},
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.interests),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    if(!isExpandedScreen){
                        IconButton(onClick = openDrawer) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            Toast.makeText(
                                context,
                                R.string.search_not_impleted,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {innerPadding ->
        val screenModifier = Modifier.padding(innerPadding)
        InterestsScreenContent(
            currentSection = currentSection,
            isExpandedScreen = isExpandedScreen,
            updateSection = onTabChange,
            tabContent = tabContent,
            modifier = screenModifier
        )
    }
}

@Composable
fun InterestsScreenContent(
    currentSection: Sections,
    isExpandedScreen: Boolean,
    updateSection: (Sections) -> Unit,
    tabContent: List<TabContent>,
    modifier: Modifier
){
    val selectedTabIndex = tabContent.indexOfFirst { it.section == currentSection }
    Column(modifier) {
        InterestsTabRow(
            selectedTabIndex = selectedTabIndex,
            updateSection = updateSection,
            tabContent = tabContent,
            isExpandedScreen = isExpandedScreen
        )
        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(0.3f)
        )
        Box(
            modifier = Modifier.weight(1f)
        ){
            tabContent[selectedTabIndex].content()
        }
    }
}

@Composable
fun InterestsTabRow(
    selectedTabIndex: Int,
    updateSection: (Sections) -> Unit,
    tabContent: List<TabContent>,
    isExpandedScreen: Boolean
){
    when(isExpandedScreen){
        false -> {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                InterestsTabRowContent(
                    selectedTabIndex = selectedTabIndex,
                    updateSection = updateSection,
                    tabsContent = tabContent,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        true -> {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                contentColor = MaterialTheme.colorScheme.primary,
                edgePadding = 0.dp
            ) {
                InterestsTabRowContent(
                    selectedTabIndex = selectedTabIndex,
                    updateSection = updateSection,
                    tabsContent = tabContent,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun InterestsTabRowContent(
    selectedTabIndex: Int,
    updateSection: (Sections) -> Unit,
    tabsContent: List<TabContent>,
    modifier: Modifier
){
    tabsContent.forEachIndexed { index, content ->
        val colorText = if(selectedTabIndex == index){
            MaterialTheme.colorScheme.primary
        }else{
            MaterialTheme.colorScheme.onSurface.copy(0.5f)
        }
        Tab(
            selected = selectedTabIndex == index,
            onClick = { updateSection(content.section) },
            modifier = modifier.heightIn(48.dp)
        ) {
            Text(
                text = stringResource(id = content.section.titleId),
                style = MaterialTheme.typography.titleMedium,
                color = colorText,
                modifier = modifier.paddingFromBaseline(20.dp)
            )
        }
    }
}
private val tabContainer = Modifier
    .fillMaxWidth()
    .wrapContentWidth(Alignment.CenterHorizontally)
@Composable
fun TabWithSection(
    sections: List<InterestsSection>,
    selectedTopics: Set<TopicSelection>,
    onTopicSelect: (TopicSelection) -> Unit
){
    Column(tabContainer.verticalScroll(rememberScrollState())) {
        sections.forEach { (section, topic) ->
            Text(
                text = section,
                modifier = Modifier
                    .padding(16.dp)
                    .semantics { heading() },
                style = MaterialTheme.typography.titleMedium
            )
            topic.forEach { topic ->
                TopicItem(
                    itemTitle = topic,
                    selected = selectedTopics.contains(TopicSelection(section, topic)),
                    onToggle = { onTopicSelect(TopicSelection(section, topic))}
                )
            }
        }
    }
}

@Composable
fun TabWithTopics(
    topics: List<String>,
    selectedTopics: Set<String>,
    onTopicSelect: (String) -> Unit
){
    topics.forEach{ topic ->
        TopicItem(
            itemTitle = topic,
            selected = selectedTopics.contains(topic),
            onToggle = { onTopicSelect(topic)}
        )
    }
}
@Composable
private fun TopicItem(
    itemTitle: String,
    selected: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = modifier.toggleable(
                value = selected,
                onValueChange = { onToggle() }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val image = painterResource(id = R.drawable.image_post_thumb)
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Text(
                text = itemTitle,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            SelectTopicButton(selected = selected)
        }
    }
}

@Composable
fun rememberTabContent(interestsViewModel: InterestsViewModel): List<TabContent>{
    val uiState by interestsViewModel.uiState.collectAsStateWithLifecycle()

    val topicsSelection = TabContent(Sections.Topic){
        val selectedTopics by interestsViewModel.selectedTopics.collectAsStateWithLifecycle()
        TabWithSection(
            sections = uiState.topics,
            selectedTopics = selectedTopics,
            onTopicSelect = { interestsViewModel.toggleTopicSelection(it) }
        )
    }

    val peopleSection = TabContent(Sections.People){
        val selectedPeople by interestsViewModel.selectedPeople.collectAsStateWithLifecycle()
        TabWithTopics(
            topics = uiState.people,
            selectedTopics = selectedPeople,
            onTopicSelect = { interestsViewModel.togglePeopleSelection(it) }
        )
    }

    val publicationSelection = TabContent(Sections.Publication){
        val selectedPublication by interestsViewModel.selectedPublication.collectAsStateWithLifecycle()
        TabWithTopics(
            topics = uiState.publications,
            selectedTopics = selectedPublication,
            onTopicSelect = { interestsViewModel.togglePublicationSelection(it) }
        )
    }
    return listOf(topicsSelection, peopleSection, publicationSelection)
}

@Preview
@Composable
fun PreviewInterestScreen(){
    val tabContent = getTabContent()
    val (currentSection, updateSection) = remember {
        mutableStateOf(tabContent.first().section)
    }
    NewsTheme {
        Surface {
            InterestsScreen(
                tabContent = tabContent,
                currentSection = currentSection,
                isExpandedScreen = false,
                onTabChange = updateSection,
                openDrawer = {},
                snackbarHostState = SnackbarHostState()
            )
        }
    }
}

@Preview
@Composable
fun PreviewInterestsScreenContent(){
    NewsTheme {
        Surface {
            InterestsScreenContent(
                currentSection = Sections.Topic,
                isExpandedScreen = false,
                updateSection = {},
                tabContent = getTabContent(),
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopicItem(){
    NewsTheme {
        Surface {
            TopicItem(
                itemTitle = "Title",
                selected = true,
                onToggle = { }
            )
        }
    }
}

private fun getTabContent():List<TabContent>{
    val interestRepository = FakeInterestsRepository()
    val topicsSection = TabContent(Sections.Topic){
        TabWithSection(
            runBlocking { (interestRepository.getTopics() as Result.Success).data },
            emptySet()
        ){}
    }
    val peopleSection = TabContent(Sections.People){
        TabWithTopics(
            runBlocking { (interestRepository.getPeople() as Result.Success).data },
            emptySet()
        ){}
    }
    val publicationSection = TabContent(Sections.Publication){
        TabWithTopics(
            runBlocking { (interestRepository.getPublications() as Result.Success).data },
            emptySet()
        ){}
    }
    return listOf(topicsSection, peopleSection, publicationSection)
}
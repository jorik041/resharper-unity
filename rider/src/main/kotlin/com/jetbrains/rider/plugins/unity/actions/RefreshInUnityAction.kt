package com.jetbrains.rider.plugins.unity.actions

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.jetbrains.rider.plugins.unity.ProjectCustomDataHost
import com.jetbrains.rider.plugins.unity.util.UnityIcons
import com.jetbrains.rider.util.idea.application
import com.jetbrains.rider.util.idea.getComponent

class RefreshInUnityAction : AnAction("Refresh", "Triggers Refresh in Unity Editor", AllIcons.Actions.Refresh) {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project?: return

        application.saveAll()
        ProjectCustomDataHost.CallBackendRefresh(project, true)
    }

    override fun update(e: AnActionEvent) {
        val projectCustomDataHost = e.getHost() ?: return

        e.presentation.isEnabled = projectCustomDataHost.sessionInitialized.value
        super.update(e)
    }
}
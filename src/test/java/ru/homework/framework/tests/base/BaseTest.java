package ru.homework.framework.tests.base;

import org.junit.After;
import org.junit.Before;
import ru.homework.framework.managers.ManagerPages;

import static ru.homework.framework.managers.InitManager.initFramework;
import static ru.homework.framework.managers.InitManager.quitFramework;

public class BaseTest {

    protected ManagerPages app = ManagerPages.getManagerPages();

    @Before
    public void before() {
        initFramework();
    }

    @After
    public void after() {
        quitFramework();
    }
}

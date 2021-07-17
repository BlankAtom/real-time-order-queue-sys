import {
    // ElAlert,
    ElAside,
    // ElAutocomplete,
    ElAvatar,
    // ElBacktop,
    // ElBadge,
    // ElBreadcrumb,
    // ElBreadcrumbItem,
    ElButton,
    // ElButtonGroup,
    // ElCalendar,
    ElCard,
    // ElCarousel,
    // ElCarouselItem,
    // ElCascader,
    // ElCascaderPanel,
    // ElCheckbox,
    // ElCheckboxButton,
    // ElCheckboxGroup,
    // ElCol,
    // ElCollapse,
    // ElCollapseItem,
    // ElCollapseTransition,
    // ElColorPicker,
    ElContainer,
    // ElDatePicker,
    // ElDialog,
    // ElDivider,
    // ElDrawer,
    ElDropdown,
    ElDropdownItem,
    ElDropdownMenu,
    ElFooter,
    ElForm,
    ElFormItem,
    ElHeader,
    ElIcon,
    // ElImage,
    ElInput,
    // ElInputNumber,
    // ElLink,
    ElMain,
    ElMenu,
    ElMenuItem,
    // ElMenuItemGroup,
    // ElOption,
    // ElOptionGroup,
    ElPageHeader,
    // ElPagination,
    // ElPopconfirm,
    // ElPopover,
    // ElPopper,
    // ElProgress,
    // ElRadio,
    // ElRadioButton,
    // ElRadioGroup,
    // ElRate,
    // ElRow,
    // ElScrollBar,
    // ElSelect,
    // ElSlider,
    // ElStep,
    // ElSteps,
    ElSubmenu,
    // ElSwitch,
    ElTabPane,
    ElTabs,
    // ElTable,
    // ElTableColumn,
    // ElTimeline,
    // ElTimelineItem,
    ElTooltip,
    // ElTransfer,
    // ElTree,
    // ElUpload,
    // ElInfiniteScroll,
    // ElLoading,
    ElMessage,
    // ElMessageBox,
    ElNotification
} from "element-plus";

export default app => {
    app.use(ElAside);
    app.use(ElAvatar);
    app.use(ElButton);
    app.use(ElCard);
    app.use(ElContainer);
    app.use(ElDropdown);
    app.use(ElDropdownItem);
    app.use(ElDropdownMenu);
    app.use(ElFooter);
    app.use(ElForm);
    app.use(ElFormItem);
    app.use(ElHeader);
    app.use(ElPageHeader);
    app.use(ElIcon);

    app.use(ElInput);
    app.use(ElMain);
    app.use(ElMenu);
    app.use(ElMenuItem);
    app.use(ElSubmenu);
    app.use(ElTabPane);
    app.use(ElTabs);
    app.use(ElTooltip);
    app.use(ElMessage);
    // app.config.globalProperties.$message = ElMessage;
    // app.use(ElMessageBox);
    app.use(ElNotification);
};

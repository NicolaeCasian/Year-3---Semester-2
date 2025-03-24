from django.urls import path
from . import views

app_name = "polls"  # Namespace for reverse resolution

urlpatterns = [
    path("", views.IndexView.as_view(), name="index"),
    path("<int:pk>/changeQuestion/", views.ChangeQuestion.as_view(), name="changeQuestion"),  # Ensure this is defined
    path("<int:pk>/changeChoice/", views.ChangeChoice.as_view(), name="changeChoice"),
    path("<int:pk>/detail/", views.DetailView.as_view(), name="detail"),
    path("<int:pk>/results/", views.ResultsView.as_view(), name="results"),
    path("<int:question_id>/vote/", views.vote, name="vote"),
]

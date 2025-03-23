from django.db import models
from django.utils import timezone
import datetime
from django.contrib import admin


class Question(models.Model):
    """
    Represents a poll question with text and a publication date.
    """
    question_text = models.CharField(max_length=200)
    pub_date = models.DateTimeField(verbose_name="date published")

    def __str__(self):
        return self.question_text

    @admin.display(
        boolean=True,
        ordering="pub_date",
        description="Published recently?",
    )

    def was_published_recently(self):
        """
        Returns True if the question was published within the last day and not in the future.
        """
        now = timezone.now()
        return now - datetime.timedelta(days=1) <= self.pub_date <= now


class Choice(models.Model):
    """
    Represents a choice for a poll question.
    """
    question = models.ForeignKey(Question, on_delete=models.CASCADE)  # Tied to a specific question
    choice_text = models.CharField(max_length=200)
    votes = models.IntegerField(default=0)  # Tracks the number of votes for this choice

    def __str__(self):
        return self.choice_text

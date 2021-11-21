package commands;

import logger.Logger;
import models.Command;
import models.User;
import services.FeedService;
import services.UserService;

import java.util.List;
import java.util.Locale;

public class UpvoteCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "upvote";

    UpvoteCommandExecutor(final FeedService feedService, final UserService userService, final Logger logger) {
        super(feedService,userService, logger);
    }
    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        return params.size() == 1;
    }

    @Override
    public void execute(Command command) {
        String feedId = command.getParams().get(0);
        User currentUser = userService.getCurrentUser();
        feedService.voteFeed(feedId, currentUser.getUuid(), COMMAND_NAME);
    }
}

package com.epam.autobasematsiuk.navigation;

import com.epam.autobasematsiuk.navigation.command.*;

/**
 * The Enum CommandEnum. Contains all the objects commands.
 */
public enum CommandEnum {

    CREATEBID {
        {
            this.command = new MoveToAddBidCommand();
        }
    },

    LISTRUNTIMEBIDS {
        {
            this.command = new ListRuntimeBidsCommand();
        }
    },

    WANTPERFORMAUTOFLIGHT {
        {
            this.command = new WantPerformAutoFlightCommand();
        }
    },

    READRULE {
        {
            this.command = new ReadRuleCommand();
        }
    },

    BACKTOADDBID {
        {
            this.command = new BackToAddBidCommand();
        }
    },

    YESADDBID {
        {
            this.command = new AddBidCommand();
        }
    },

    CHANGEBID {
        {
            this.command = new ChangeBidCommand();
        }
    },

    WANTCHANGEBID {
        {
            this.command = new WantChangeBidCommand();
        }
    },

    TOAUTOFLIGHT {
        {
            this.command = new ListAutoFlightsCommand();
        }
    },

    INFOABOUTAUTOFLIGHT {
        {
            this.command = new InfoAutoFlightCommand();
        }
    },

    CANCELAUTOFLIGHT {
        {
            this.command = new CancelAutoFlightCommand();
        }
    },

    LISTPERFORMEDBIDS {
        {
            this.command = new ListPerformedBidsCommand();
        }
    },

    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    },

    TOBIDSDRIVER {
        {
            this.command = new BackToDriverBidCommand();
        }
    },

    REPAIR {
        {
            this.command = new RepairAutoCommand();
        }
    },

    LISTAUTOFORREPAIR {
        {
            this.command = new AutoForRepairCommand();
        }
    },

    LOOKLISTAUTO {
        {
            this.command = new ListAutoCommand();
        }
    },

    CHANGECONDITIONAUTO {
        {
            this.command = new DriverReportCommand();
        }
    },

    PERFORMAUTOFLIGHT {
        {
            this.command = new PerformAutoFlightCommand();
        }
    },

    CREATEAUTOFLIGHT {
        {
            this.command = new AddAutoFlightCommand();
        }
    },

    ACCEPTBID {
        {
            this.command = new AcceptBidCommand();
        }
    },

    LISTNEWBIDS {
        {
            this.command = new ListNewBidsCommand();
        }
    },

    NOTDELETE {
        {
            this.command = new CancelDeletionBidCommand();
        }
    },

    WANTDELETEBID {
        {
            this.command = new WantDeleteBidCommand();
        }
    },

    DELETEBID {
        {
            this.command = new DeleteBidCommand();
        }
    },

    WANTCREATEBID {
        {
            this.command = new WantAddBidCommand();
        }
    },

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },

    LANG {
        {
            this.command = new LangCommand();
        }
    },

    STARTPAGE {
        {
            this.command = new EmptyCommand();
        }
    },

    WANTREGISTRATION {
        {
            this.command = new WantRegistrationCommand();
        }
    },

    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    };

    ActionCommand command;

    /**
     * Gets the current command.
     *
     * @return current command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
